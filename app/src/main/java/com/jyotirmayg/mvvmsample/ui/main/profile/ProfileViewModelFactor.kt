package com.jyotirmayg.mvvmsample.ui.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jyotirmayg.mvvmsample.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactor(private val userRepository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(userRepository) as T
    }
}