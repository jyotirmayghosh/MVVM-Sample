package com.jyotirmayg.mvvmsample.data.repositories

import com.jyotirmayg.mvvmsample.data.db.AppDatabase
import com.jyotirmayg.mvvmsample.data.db.entities.User
import com.jyotirmayg.mvvmsample.data.network.MyAPICalls
import com.jyotirmayg.mvvmsample.data.network.SafeAPIRequest
import com.jyotirmayg.mvvmsample.data.network.response.AuthResponse

class UserRepository(private val api: MyAPICalls, private val db: AppDatabase) : SafeAPIRequest() {

    /**
     * Network calls
     */
    suspend fun userLogin(email: String, password: String): AuthResponse {
        return safeAPIRequest { api.userLogin(email, password) }
    }

    suspend fun userSignup(name: String, email: String, password: String): AuthResponse {
        return safeAPIRequest { api.userSignup(name, email, password) }
    }

    /**
     * Database calls
     */
    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()
}