package com.melvin.ongandroid.model.data

import com.google.gson.annotations.SerializedName

data class WelcomeImage(val image: String,
                        @SerializedName("name")
                        val tittle: String,
                        val description: String
                       )