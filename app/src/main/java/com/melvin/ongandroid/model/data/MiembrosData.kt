package com.melvin.ongandroid.model.data

import com.google.gson.annotations.SerializedName

data class MiembrosData<T> (
    @SerializedName("success") val success : Boolean,
    @SerializedName("data") val data : T,
    @SerializedName("message")val message : String
        )
