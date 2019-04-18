package com.example.todoapp

import android.content.Context
import android.widget.Toast

fun Context.toast(message: String, longLength: Boolean = false) {
    var toastLength: Int = Toast.LENGTH_SHORT
    if (longLength) toastLength = Toast.LENGTH_LONG
    Toast.makeText(this, message, toastLength).show()
}