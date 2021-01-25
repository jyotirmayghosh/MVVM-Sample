package com.jyotirmayg.mvvmsample.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.jyotirmayg.mvvmsample.data.repositories.UserRepository
import com.jyotirmayg.mvvmsample.util.Coroutines

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


        //Add coroutine for long running task :: Network calls
        Coroutines.main {
            val response = UserRepository().userLogin(email!!, password!!)
            if (response.isSuccessful){
                authListener?.onSuccess(response.body()?.user!!)
            } else {
                authListener?.onFailure("Error: ${response.code()}")
            }
        }
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

        Coroutines.main {
            val response = UserRepository().userSignup(name!!, email!!, password!!)
            if (response.isSuccessful){
                authListener?.onSuccess(response.body()?.user!!)
            } else{
                authListener?.onFailure("Error: ${response.code()}")
            }
        }
    }
}