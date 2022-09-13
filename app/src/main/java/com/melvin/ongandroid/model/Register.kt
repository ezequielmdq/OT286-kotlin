package com.melvin.ongandroid.model

import com.google.gson.annotations.SerializedName

data class Register(@SerializedName("name") var name: String = "",
                    @SerializedName("email") var email: String = "",
                    @SerializedName("password") var passwword: String = "")
