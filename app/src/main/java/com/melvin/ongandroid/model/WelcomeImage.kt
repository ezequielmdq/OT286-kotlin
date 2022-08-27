package com.melvin.ongandroid.model

import com.google.gson.annotations.SerializedName

data class WelcomeImage(val image: String,
                        @SerializedName("name")
                        val tittle: String,
                        val description: String
                       )