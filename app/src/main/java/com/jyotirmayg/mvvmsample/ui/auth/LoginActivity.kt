package com.jyotirmayg.mvvmsample.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jyotirmayg.mvvmsample.R
import com.jyotirmayg.mvvmsample.data.db.entities.User
import com.jyotirmayg.mvvmsample.databinding.ActivityLoginBinding
import com.jyotirmayg.mvvmsample.ui.main.MainActivity
import com.jyotirmayg.mvvmsample.util.*
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactor by instance<AuthViewModelFactor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewmodel = viewModel

        viewModel.authListener = this

        viewModel.getLoggedUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, MainActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        progress_circular.show()
    }

    override fun onSuccess(user: User) {
        progress_circular.hide()
        toast("${user.name} logged in.")
    }

    override fun onFailure(message: String) {
        progress_circular.hide()
        rootLayout.snackbar(message)
        print(message)
    }
}