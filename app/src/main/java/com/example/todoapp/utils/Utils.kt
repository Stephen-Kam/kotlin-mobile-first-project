package com.example.todoapp.utils

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.widget.ActionBarContextView
import android.view.View
import android.widget.Toast
import com.example.todoapp.R

fun Context.toast(message: String, longLength: Boolean = false) {
    var toastLength: Int = Toast.LENGTH_SHORT
    if (longLength) toastLength = Toast.LENGTH_LONG
    Toast.makeText(this, message, toastLength).show()
}
//
//fun Context.showSnackbar(message: String) {
//    Snackbar
//        .make(this, "", Snackbar.LENGTH_INDEFINITE)
//        .setAction(getString(R.string.snack_main_action)) {}
//        .show()
//}