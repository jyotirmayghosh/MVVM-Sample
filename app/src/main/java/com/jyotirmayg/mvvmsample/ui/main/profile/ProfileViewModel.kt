package com.jyotirmayg.mvvmsample.ui.main.profile

import androidx.lifecycle.ViewModel
import com.jyotirmayg.mvvmsample.data.repositories.UserRepository

class ProfileViewModel(userRepository: UserRepository) : ViewModel() {
    val user = userRepository.getUser()
}