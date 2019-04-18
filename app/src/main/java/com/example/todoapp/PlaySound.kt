package com.example.todoapp

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech

class PlaySound : Speaker {

    /**
     * Initialise the TextToSpeech variable at a later time
     */
    lateinit var tts: TextToSpeech

    fun init(context: Context) {
        tts = TextToSpeech(context, null)
    }

    override fun speak(text: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        } else {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    fun onDestroy() {
        tts.shutdown()
    }
}

interface Speaker {
    fun speak(text: String)
}