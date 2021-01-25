package com.jyotirmayg.mvvmsample.data.network.response

import com.jyotirmayg.mvvmsample.data.db.entities.User

data class AuthResponse(
    val isSuccess: Boolean?,
    val message: String?,
    val user: User?
)