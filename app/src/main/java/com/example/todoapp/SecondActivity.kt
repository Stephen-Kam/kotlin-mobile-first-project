//package com.example.todoapp
//
//import android.app.Activity
//import android.content.Intent
//import android.graphics.Bitmap
//import android.os.Bundle
//import android.os.Parcelable
//import android.provider.MediaStore
//import android.support.v7.app.AppCompatActivity
//import android.view.Menu
//import android.view.MenuItem
//import kotlinx.android.synthetic.main.activity_second.*
//
//class SecondActivity : AppCompatActivity() {
//
//    private val text by argString(TEXT_ARG)
//    private val user by arg<User>(USER_ARG)
//    private val REQUEST_IMAGE_CAPTURE  = 1
//    private val prefRepo = PrefRepo(this)
//
//
//    private fun dispatchTakePitureIntent() {
//        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        if (takePictureIntent.resolveActivity(packageManager) != null) {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        when {
//            requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK -> {
//                val imageBitmap = data?.extras?.get("data") as Bitmap
//                second_image.setImageBitmap(imageBitmap)
//            }
//            else -> super.onActivityResult(requestCode, resultCode, data)
//        }
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        getMenuInflater().inflate(R.menu.main_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean =
//        when (item.getItemId()) {
//            R.id.menu_camera -> {
//                dispatchTakePitureIntent()
//                true
//            }
//            R.id.menu_logout -> {
//                val username = prefRepo.getUsername() ?: throw Error("User is not logged in")
//                LoginActivity.start(this)
//                toast("$username is logged out", true)
//                prefRepo.removeUsername()
//                finish()
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_second)
//        second_textView.text = "Text is $text, user is ${user.name} ${user.surname}"
//
//        val username = prefRepo.getUsername() ?: throw Error("User is not logged it")
//
//        toast("$username is logged in", true)
//
//    }
//
//    companion object {
//        val TEXT_ARG = "TEXT_ARG"
//        val USER_ARG = "USER_ARG"
//
//        fun start(activity: Activity, message: String, user: User) {
//            val intent = Intent(activity, SecondActivity::class.java).apply {
//                putExtra(TEXT_ARG, message)
//                putExtra(USER_ARG, user)
//            }
//            activity.startActivity(intent)
//        }
//    }
//}
//
//fun Activity.argString(key: String) = lazy { intent.getStringExtra(key) }
//fun <T : Parcelable> Activity.arg(key: String) = lazy { intent.getParcelableExtra<T>(key) }
