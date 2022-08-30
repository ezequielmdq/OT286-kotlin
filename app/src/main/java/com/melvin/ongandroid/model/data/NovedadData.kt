package com.melvin.ongandroid.model.data

import com.google.gson.annotations.SerializedName
import com.melvin.ongandroid.model.Novedad

data class NovedadData<T>(

    @SerializedName("success") val success : Boolean,
    @SerializedName("data") val data : T,
    @SerializedName("message") val messsage: String

    // la api siempre retorna una estructura con los campos success, data, message

    //val image : String

)
