package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var timeLimit: TextView
    lateinit var startingPoint: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeLimit = findViewById(R.id.textView)
        startingPoint = findViewById(R.id.button)

        startingPoint.setOnClickListener {
            CoroutineScope(Job() + Dispatchers.Main).launch {
                repeat(100) {
                    (100 - it).toString().run {
                        Log.d("Countdown", this)
                        withContext(Dispatchers.Main) {
                            timeLimit.text = this@run
                        }
                    }
                    delay(1000)
                }
            }
        }
    }
}