package com.melvin.ongandroid.model.data

import com.melvin.ongandroid.model.WelcomeImage

data class WelcomeData(
    val success : Boolean,
    val data : List<WelcomeImage>,
    val image : String
)
