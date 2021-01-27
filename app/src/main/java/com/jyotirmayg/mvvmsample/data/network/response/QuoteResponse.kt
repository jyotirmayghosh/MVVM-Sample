package com.jyotirmayg.mvvmsample.data.network.response

import com.jyotirmayg.mvvmsample.data.db.entities.Quote

class QuoteResponse(
    val isSuccessful: Boolean?,
    val quotes: List<Quote>?
)