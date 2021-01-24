package com.jyotirmayg.mvvmsample.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.jyotirmayg.mvvmsample.data.repositories.UserRepository

class AuthViewModel : ViewModel() {

    var authListener: AuthListener? = null

    var name: String? = null
    var email: String? = null
    var password: String? = null

    fun onLoginButtonClicked(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }

        val loginResult = UserRepository().userLogin(email!!, password!!)
        authListener?.onSuccess(loginResult)
    }

    fun doSignupButtonClicked(view: View) {
        view.context.startActivity(Intent(view.context, SignupActivity::class.java))
    }

    fun onSignupButtonClicked(view: View) {
        authListener?.onStarted()
        if (name.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("All fields are mandatory")
            return
        }

        val signupResult = UserRepository().userSignup(name!!, email!!, password!!)
        authListener?.onSuccess(signupResult)
    }
}