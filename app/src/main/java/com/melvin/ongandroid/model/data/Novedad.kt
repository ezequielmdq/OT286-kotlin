package com.melvin.ongandroid.model.data

import com.google.gson.annotations.SerializedName

data class Novedad(

    @SerializedName("name")
    val titulo: String,
    @SerializedName("image")
    val imagen: String,
    @SerializedName("content")
    val descripcion: String

    )