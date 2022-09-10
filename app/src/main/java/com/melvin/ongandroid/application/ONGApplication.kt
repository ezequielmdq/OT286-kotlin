package com.melvin.ongandroid.application

import android.app.Application

class ONGApplication : Application() {
    companion object{
        lateinit var prefs : Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}