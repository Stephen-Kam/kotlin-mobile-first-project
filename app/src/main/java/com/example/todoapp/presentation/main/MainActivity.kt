package com.example.todoapp.presentation.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.todoapp.R
import com.example.todoapp.data.StorageRepoImpl
import com.example.todoapp.models.User
import com.example.todoapp.presentation.fragments.contact.ContactFragment
import com.example.todoapp.presentation.fragments.photo.PhotoFragment
import com.example.todoapp.presentation.fragments.settings.SettingsFragment
import com.example.todoapp.presentation.login.LoginActivity
import com.example.todoapp.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val prefRepo = StorageRepoImpl(this)


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_photo -> {
                show(PhotoFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_contact -> {
                show(ContactFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                show(SettingsFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.getItemId()) {
            R.id.main_menu_bug -> {
                toast("Bug menu clicked")
                true
            }
            R.id.main_menu_logout -> {
                val username = prefRepo.getUsername() ?: throw Error("User is not logged in")
                LoginActivity.start(this)
                toast("$username is logged out", true)
                prefRepo.removeUsername()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        show(PhotoFragment())
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val username = prefRepo.getUsername() ?: throw Error("User is not logged it")

        toast("$username is logged in", true)
    }

    fun show(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    companion object {
        fun start(activity: Activity, message: String, user: User) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
