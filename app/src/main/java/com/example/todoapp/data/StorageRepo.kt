package com.example.todoapp.data

import android.content.Context
import android.preference.PreferenceManager

interface StorageRepo {
    fun getEntryCount(): Int
    fun setEntryCount(num: Int)
    fun getUsername(): String?
    fun setUsername(username: String)
    fun removeUsername()
}

class StorageRepoImpl(context: Context): StorageRepo {

    private val pref by lazy { PreferenceManager.getDefaultSharedPreferences(context) }

    override fun getEntryCount(): Int = pref.getInt(ENTRY_COUNT_KEY, 0)

    override fun setEntryCount(num: Int) {
        pref.edit().putInt(ENTRY_COUNT_KEY, num).apply()
    }

    override fun getUsername(): String? = pref.getString(EMAIL_KEY, null)

    override fun setUsername(email: String) {
        pref.edit().putString(EMAIL_KEY, email).apply()
    }

    override fun removeUsername() {
        pref.edit().remove(EMAIL_KEY).apply()
    }

    companion object {
        private const val ENTRY_COUNT_KEY = "ENTRY_COUNT_KEY"
        private const val EMAIL_KEY = "EMAIL_KEY"
    }
}