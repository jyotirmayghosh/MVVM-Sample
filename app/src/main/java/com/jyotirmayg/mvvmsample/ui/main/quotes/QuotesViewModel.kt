package com.jyotirmayg.mvvmsample.ui.main.quotes

import androidx.lifecycle.ViewModel
import com.jyotirmayg.mvvmsample.data.repositories.QuoteRepository
import com.jyotirmayg.mvvmsample.util.lazyDeferred

class QuotesViewModel(quoteRepository: QuoteRepository) : ViewModel() {

    val quotes by lazyDeferred {
        quoteRepository.getQuotes()
    }

}