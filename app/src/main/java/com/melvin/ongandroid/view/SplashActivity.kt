package com.melvin.ongandroid.view


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityScope.launch {
            delay(5000)
            finish()

        }
    }

    override fun finish() {
        activityScope.cancel()
        super.finish()
        Toast.makeText(this, "Timer has finish", Toast.LENGTH_LONG).show()
    }
}
