package com.example.todoapp.presentation.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import com.example.todoapp.R
import com.example.todoapp.data.PlaySound
import com.example.todoapp.data.StorageRepoImpl
import com.example.todoapp.models.User
import com.example.todoapp.presentation.main.MainActivity
import com.example.todoapp.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    private val presenter by lazy { LoginPresenter(this, StorageRepoImpl(this), PlaySound(this)) }

    override fun getUsername(): String = txt_Username.text.toString()

    override fun getPassword(): String = txt_Password.text.toString()

    override fun showToastMessage(message: String) = toast(message)

    override fun switchToMainActivity(username: String) {
        MainActivity.start(this, "some message", User("Marcin", "Moskała"))
        finish()
    }

    override fun showErrorMessage(message: String) {
        txt_Error.text = message
    }

    override fun hideSubmitButton() {
        btn_Submit.setAlpha(.5f)
        btn_Submit.setClickable(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.login_menu, menu)
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

    override fun startShareMessageActivity(message: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }.let { Intent.createChooser(it, "Share using:") }

        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(sendIntent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.onStart()

        txt_Password.setOnEditorActionListener { _, actionId, event ->
            val isActionLogin = (actionId == EditorInfo.IME_ACTION_GO ||
                    event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)
            if (isActionLogin) {
                presenter.onLoginClicked()
            }
            isActionLogin
        }

        btn_Submit.setOnClickListener {
            presenter.onLoginClicked()
        }

        btn_register.setOnClickListener {
            toast("Register button clicked")
            presenter.saySomething("Register button clicked")
        }

        btn_Forgotten.setOnClickListener {
            toast("Forgotten Details button clicked")
            presenter.saySomething("Forgotten Details button clicked")
        }
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
