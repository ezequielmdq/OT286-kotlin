package com.melvin.ongandroid.model.data

import com.google.gson.annotations.SerializedName

data class RegisterData(@SerializedName("succes") var succes: Boolean,
                        @SerializedName("message") var message: String = "")
