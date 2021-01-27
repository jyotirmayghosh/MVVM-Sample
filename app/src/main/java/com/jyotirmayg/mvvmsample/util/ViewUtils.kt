package com.jyotirmayg.mvvmsample.util

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun print(message: String) {
    Log.d("tag", message)
}

fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE).also { snackbar ->
        snackbar.setAction("OK") { snackbar.dismiss() }
    }.show()
}