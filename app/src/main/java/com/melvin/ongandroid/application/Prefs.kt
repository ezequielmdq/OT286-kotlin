package com.melvin.ongandroid.application

import android.content.Context

class Prefs(context: Context) {

    val SHARED_NAME = "com.melvin.ongandroid"
    val SHARED_TOKEN = "TOKEN_ID"

    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveToken(token: String){
        storage.edit().putString(SHARED_TOKEN, token).apply()
    }

    fun getToken() : String? {
        return storage.getString(SHARED_TOKEN, "")
    }
}