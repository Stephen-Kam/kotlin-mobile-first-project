package com.example.todoapp.presentation.login

import com.example.todoapp.data.PlaySound
import com.example.todoapp.data.StorageRepo

class LoginPresenter(
    private val view: LoginView,
    private val storageRepo: StorageRepo,
    private val speaker: PlaySound) {

    private var counter = 0
    private var errorCount = 0
    private val invalidCount = 5
    private val validUsers = listOf<String>("Adam", "Betty", "Charlie", "Danielle")

    fun onStart() {
        checkIfNotLogged()
        speaker.init()
    }

    fun onDestroy() {
        speaker.onDestroy()
    }

    fun onMessageTaskClicked() {
        saySomething("I say something random!")
    }

    fun onSendTaskClicked() {
        view.startShareMessageActivity("Some text")
    }

    fun saySomething(message: String) {
        speaker.speak(message)
    }

    private fun loginSuccess(username: String) {
        storageRepo.setUsername(username)
        view.switchToMainActivity(username)
    }

    private fun checkIfNotLogged() {
        val usernameInPreferences = storageRepo.getUsername()
        if (usernameInPreferences != null) {
            loginSuccess(usernameInPreferences)
        }
    }

    fun onLoginClicked() {
        val username = view.getUsername()
        val password = view.getPassword()
        if (username == "" || password.isEmpty()) {
            view.showErrorMessage("Fields cannot be empty")
            speaker.speak("Fields cannot be empty")
        } else if ((validUsers.contains(username) && password == "1234567890")) {
            speaker.speak("Success!")
            loginSuccess(username)
        } else {
            when (errorCount) {
                invalidCount -> {
                    view.showErrorMessage("Too many attempts, try again later or click Forgotten Password")
                    speaker.speak("Too many attempts")
                    view.hideSubmitButton()
                }
                else -> {
                    errorCount++
                    view.showErrorMessage("Invalid details. Attempts failed: $errorCount")
                    speaker.speak("Invalid details. Attempts failed: $errorCount")
                }
            }
        }
    }
}