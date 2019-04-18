package com.example.todoapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {


    private var errorCount = 0
    private val invalidCount = 5
    private var snackbarSeen = false
    private val speaker = PlaySound()
    private val prefRepo = PrefRepo(this)
    private val validUsers = listOf<String>("Adam", "Betty", "Charlie", "Danielle")

    /**
     * Menu stuff
     * 1. Initialise the menu
     * 2. For each menu item, do something
     * 3. A function for when a menu item is clicked
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.login_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.getItemId()) {
            R.id.login_menu_bug -> {
                toast("Bug menu clicked")
                true
            }
            R.id.login_menu_settings -> {
                toast("Settings menu clicked")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    /**
     * Everything that happens when the app is created
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val contextView: View = container
        speaker.init(this)
        if (prefRepo.getUsername() != null) {
            startMain()
        }

        fun login() {
            val username = txt_Username.text.toString()
            val password = txt_Password.text.toString()
            if (username == "" || password.isEmpty()) {
                txt_Error.text = getString(R.string.error_emptyFields)
                speaker.speak(getString(R.string.error_emptyFields))
            } else if ((validUsers.contains(username) && password == "1234567890")) {
                speaker.speak("Success!")
                loginSuccess(username)
            } else {
                when (errorCount) {
                    invalidCount -> {
                        speaker.speak("Too many attempts")
                        btn_Submit.visibility = View.GONE
                    }
                    else -> {
                        errorCount++
                        txt_Error.text = getString(R.string.main_attempts_failed, errorCount)
                        speaker.speak(getString(R.string.main_attempts_failed, errorCount))
                    }
                }
            }
        }

        btn_Submit.setOnClickListener {
            login()
        }

        btn_register.setOnClickListener {
            toast("Register button clicked")
            speaker.speak("Register button clicked")
        }

        btn_Forgotten.setOnClickListener {
            toast("Forgotten Details button clicked")
            speaker.speak("Forgotten Details button clicked")
        }

        if (!snackbarSeen) {
            Snackbar
                .make(contextView, getString(R.string.snack_main), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.snack_main_action)) {}
                .show()
            snackbarSeen = true
        }

        txt_Password.setOnEditorActionListener { _, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_GO -> {
                    btn_Submit.callOnClick()
                    true
                }
                else -> false
            }
        }
    }

    /**
     * What happens when the app is closed
     */
    override fun onDestroy() {
        super.onDestroy()
        speaker.onDestroy()
    }

    private fun loginSuccess(username: String) {
        prefRepo.setUsername(username)
        startMain()
    }

    private fun startMain() {
        MainActivity.start(this, "THIS IS SOME TEXT", User("Marcin", "Moskała"))
        finish()
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
        }
    }
}


