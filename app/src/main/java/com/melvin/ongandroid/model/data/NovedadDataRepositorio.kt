package com.melvin.ongandroid.model.data

import com.melvin.ongandroid.model.AlkemyAPIClient
import com.melvin.ongandroid.model.Novedad

class NovedadDataRepositorio {

    //Repositorio novedades

    suspend fun getDataNovedad() : List<Novedad>? {
        try{
            val servicio = AlkemyAPIClient.getClient().getDataNovedad().body()
            val data = servicio?.data
            return data
        } catch(e: Exception){
            return emptyList()

        }

    }


}