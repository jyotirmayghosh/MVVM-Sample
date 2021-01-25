package com.jyotirmayg.mvvmsample.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jyotirmayg.mvvmsample.R
import com.jyotirmayg.mvvmsample.data.db.entities.User
import com.jyotirmayg.mvvmsample.databinding.ActivitySignupBinding
import com.jyotirmayg.mvvmsample.util.hide
import com.jyotirmayg.mvvmsample.util.print
import com.jyotirmayg.mvvmsample.util.show
import com.jyotirmayg.mvvmsample.util.toast
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity(), AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        val binding: ActivitySignupBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_signup)

        binding.viewmodel = viewModel

        viewModel.authListener = this
    }

    override fun onStarted() {
        progress_circular.show()
    }

    override fun onSuccess(user: User) {
        progress_circular.hide()
        print(user.name)
        toast(user.name!!)
    }

    override fun onFailure(message: String) {
        toast(message)
        print(message)
        progress_circular.hide()
    }
}