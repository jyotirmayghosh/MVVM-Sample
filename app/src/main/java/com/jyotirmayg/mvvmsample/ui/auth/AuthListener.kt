package com.jyotirmayg.mvvmsample.ui.auth

import androidx.lifecycle.LiveData

interface AuthListener {
    fun onStarted()
    fun onSuccess(result: LiveData<String>)
    fun onFailure(message: String)
}