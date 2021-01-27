package com.jyotirmayg.mvvmsample.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jyotirmayg.mvvmsample.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactor(private val userRepository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(userRepository) as T
    }
}