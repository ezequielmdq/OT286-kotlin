package com.melvin.ongandroid.model.data

import com.melvin.ongandroid.model.AlkemyAPIClient

class WelcomeDataRepositorio {

    //Repositorio slide

    suspend fun getDataSlide(): List<WelcomeImage>? {
        try {
            val servicio = AlkemyAPIClient.getClient().getData().body()
            val data = servicio?.data
            return data
        } catch (e: Exception) {
            return emptyList()

        }
    }
}


