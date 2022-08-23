package com.melvin.ongandroid.model.data

import com.melvin.ongandroid.model.AlkemyAPIClient

class NovedadDataRepositorio {

    suspend fun getDataNovedad() : List<Novedad>? {
        val servicio = AlkemyAPIClient.getClient().getDataNovedad().body()
        val data = servicio?.data
        return data

    }


}