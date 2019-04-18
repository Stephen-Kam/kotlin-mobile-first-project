package com.example.todoapp

import android.content.Context
import android.preference.PreferenceManager

class PrefRepo(context: Context) {

    private val pref by lazy { PreferenceManager.getDefaultSharedPreferences(context) }

    fun getEntryCount(): Int = pref.getInt(ENTRY_COUNT_KEY, 0)

    fun setEntryCount(num: Int) {
        pref.edit().putInt(ENTRY_COUNT_KEY, num).apply()
    }

    fun getUsername(): String? = pref.getString(USERNAME_KEY, null)

    fun setUsername(email: String) {
        pref.edit().putString(USERNAME_KEY, email).apply()
    }

    fun removeUsername() {
        pref.edit().remove(USERNAME_KEY).apply()
    }

    companion object {
        private const val ENTRY_COUNT_KEY = "ENTRY_COUNT_KEY"
        private const val USERNAME_KEY = "EMAIL_KEY"
    }
}