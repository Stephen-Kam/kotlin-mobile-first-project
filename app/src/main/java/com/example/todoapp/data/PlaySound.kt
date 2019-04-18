package com.example.todoapp.data

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech

class PlaySound(val context: Context): Speaker {

    lateinit var tts: TextToSpeech

    override fun init() {
        tts = TextToSpeech(context, null)
    }

    override fun speak(text: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        } else {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    override fun onDestroy() {
        tts.shutdown()
    }
}

interface Speaker {
    fun init()
    fun speak(text: String)
    fun onDestroy()
}