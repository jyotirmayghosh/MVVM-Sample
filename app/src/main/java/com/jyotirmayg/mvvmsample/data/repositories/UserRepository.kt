package com.jyotirmayg.mvvmsample.data.repositories

import com.jyotirmayg.mvvmsample.data.network.MyAPICalls
import com.jyotirmayg.mvvmsample.data.network.response.AuthResponse
import retrofit2.Response

class UserRepository {

    suspend fun userLogin(email: String, password: String): Response<AuthResponse> {
       return MyAPICalls.invoke().userLogin(email, password)
    }

    suspend fun userSignup(name: String, email: String, password: String): Response<AuthResponse>{
        return MyAPICalls.invoke().userSignup(name, email, password)
    }
}