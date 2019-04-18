package com.example.todoapp


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    private val prefRepo by lazy { PrefRepo(requireContext()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_settings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_settings_logout.setOnClickListener { logout() }
    }

    private fun logout() {
        val username = prefRepo.getUsername() ?: throw Error("User is not logged in")
        prefRepo.removeUsername()
        val activity = activity!!
        LoginActivity.start(activity)
        Toast.makeText(context, "$username is logged out", Toast.LENGTH_LONG).show()
        activity.finish()
    }
}
