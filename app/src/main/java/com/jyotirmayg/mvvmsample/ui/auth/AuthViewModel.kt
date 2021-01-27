package com.jyotirmayg.mvvmsample.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.jyotirmayg.mvvmsample.data.repositories.UserRepository
import com.jyotirmayg.mvvmsample.util.APIExceptions
import com.jyotirmayg.mvvmsample.util.Coroutines
import com.jyotirmayg.mvvmsample.util.NoInternetExceptions

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    var authListener: AuthListener? = null

    var name: String? = null
    var email: String? = null
    var password: String? = null

    fun getLoggedUser() = repository.getUser()

    fun onLoginButtonClicked(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }


        //Add coroutine for long running task :: Network calls
        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: APIExceptions) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetExceptions) {
                authListener?.onFailure(e.message!!)
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
            try {
                val authResponse = repository.userSignup(name!!, email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    return@main
                }
                authListener?.onFailure("Error: ${authResponse.message}")
            } catch (e: APIExceptions) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetExceptions) {
                authListener?.onFailure(e.message!!)
            }
        }
    }
}