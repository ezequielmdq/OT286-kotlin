package com.melvin.ongandroid.model.data

import com.melvin.ongandroid.model.AlkemyAPIClient

class WelcomeDataRepositorio {

    suspend fun getDataSlide() : List<WelcomeImage>? {
        val servicio = AlkemyAPIClient.getClient().getData().body()
        val data = servicio?.data
        return data
    }



}