package com.example.todoapp.presentation.fragments.contact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.R
import com.example.todoapp.data.StorageRepo
import com.example.todoapp.data.StorageRepoImpl
import kotlinx.android.synthetic.main.fragment_contact.*

class ContactFragment : Fragment() {

    private val prefRepo by lazy { StorageRepoImpl(requireContext()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_contact, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_sendEmail.setOnClickListener { onEmailTaskClicked("") }
        btn_sendMessage.setOnClickListener { onMessageTaskClicked("") }
    }

    private fun onMessageTaskClicked(message: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra("address", "07787123456")
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }
        if (sendIntent.resolveActivity(activity!!.packageManager) != null) startActivity(sendIntent)
    }

    private fun onEmailTaskClicked(message: String) {
        val uri = Uri.parse("mailto:emailaddress@emailaddress.com")
            .buildUpon()
            .appendQueryParameter("subject", "Question")
            .appendQueryParameter("body", "")
            .build()

        val emailIntent = Intent(Intent.ACTION_SENDTO, uri)
        startActivity(Intent.createChooser(emailIntent, "Email to Dev"))
    }

}