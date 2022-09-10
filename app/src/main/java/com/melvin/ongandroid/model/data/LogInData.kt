package com.melvin.ongandroid.model.data

import com.google.gson.annotations.SerializedName
import com.melvin.ongandroid.model.Token

data class LogInData(@SerializedName("success")val success : Boolean,
                     @SerializedName("data") val data: Token,
                     @SerializedName("message") val message : String)