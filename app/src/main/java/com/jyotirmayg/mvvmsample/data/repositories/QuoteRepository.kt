package com.jyotirmayg.mvvmsample.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jyotirmayg.mvvmsample.data.db.AppDatabase
import com.jyotirmayg.mvvmsample.data.db.entities.Quote
import com.jyotirmayg.mvvmsample.data.network.MyAPICalls
import com.jyotirmayg.mvvmsample.data.network.SafeAPIRequest
import com.jyotirmayg.mvvmsample.data.preferences.PreferenceProvider
import com.jyotirmayg.mvvmsample.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class QuoteRepository(
    private val api: MyAPICalls,
    private val db: AppDatabase,
    private val pref: PreferenceProvider
) : SafeAPIRequest() {

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>> {
        return withContext(Dispatchers.IO) {
            fetchQuotesFromServer()
            fetchQuotesFromLocal()
        }
    }

    private fun fetchQuotesFromLocal(): LiveData<List<Quote>> {
        return db.getQuotesDao().getQuotes()
    }

    private suspend fun fetchQuotesFromServer() {
        val lastSavedAt = pref.getLastSavedAt()
        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            val quoteResponse = safeAPIRequest { api.getQuotes() }
            quotes.postValue(quoteResponse.quotes)
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            pref.saveLastSavedAt(LocalDateTime.now().toString())
            db.getQuotesDao().saveAllQuotes(quotes)
        }
    }

    companion object{
        private const val MINIMUM_INTERVAL = 6
    }
}