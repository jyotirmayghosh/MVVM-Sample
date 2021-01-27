package com.jyotirmayg.mvvmsample.ui.main.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jyotirmayg.mvvmsample.data.repositories.QuoteRepository

@Suppress("UNCHECKED_CAST")
class QuotesViewModelFactor(private val repository: QuoteRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
    }
}