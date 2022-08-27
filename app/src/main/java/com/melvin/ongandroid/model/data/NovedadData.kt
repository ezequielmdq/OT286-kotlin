package com.melvin.ongandroid.model.data

import com.google.gson.annotations.SerializedName
import com.melvin.ongandroid.model.Novedad

data class NovedadData<T>(

    @SerializedName("success")val success : Boolean,
    @SerializedName("data") val data : T,
    @SerializedName("message") val message : String
// la api siempre retorna una estructura con los campos succes, data, message,  camb

)

//data class NovedadResponse(
//
//    @SerializedName("name") val name : String,
//    @SerializedName("content") val content: String,
//    @SerializedName("image") val image: String,
//
//   )
