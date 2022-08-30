package com.melvin.ongandroid.model

import com.google.gson.annotations.SerializedName

data class Novedad(
    @SerializedName("name")
    val titulo: String,
    @SerializedName("image")
    val imagen: String,
    @SerializedName("content")
    val descripcion: String,

    // Añadir más campos para mostrar la lista de novedades

    @SerializedName("id") val id: Int

    )