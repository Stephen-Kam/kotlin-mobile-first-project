package com.example.todoapp.presentation.login

interface LoginView {
    fun hideSubmitButton()
    fun startShareMessageActivity(message: String)
    fun showToastMessage(message: String)
    fun showErrorMessage(message: String)
    fun switchToMainActivity(username: String)
    fun getUsername(): String
    fun getPassword(): String
}