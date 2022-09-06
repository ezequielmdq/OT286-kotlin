package com.melvin.ongandroid.view


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.melvin.ongandroid.R
import com.melvin.ongandroid.application.ONGApplication
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    val activityScope = CoroutineScope(Dispatchers.Main)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setTheme(R.style.SplashTheme_NoActionBar)
        supportActionBar?.hide()

        activityScope.launch {
            //delay(1000)
            verificacionDeUsuario()
        }
    }

    private fun verificacionDeUsuario() {
        if(ONGApplication.prefs.getToken().isNullOrEmpty()){
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }else{
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }

    override fun finish() {
        activityScope.cancel()
        super.finish()
        //Toast.makeText(this, "Timer has finish", Toast.LENGTH_LONG).show()
    }
}
