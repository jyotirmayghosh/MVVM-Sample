package com.jyotirmayg.mvvmsample.ui.auth

import com.jyotirmayg.mvvmsample.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}