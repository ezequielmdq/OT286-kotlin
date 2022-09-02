package com.melvin.ongandroid.model.repository.Network.implement

import com.melvin.ongandroid.model.Miembros
import com.melvin.ongandroid.model.repository.Network.interfaces.IMiembrosDataRepository
import com.melvin.ongandroid.model.repository.Network.retrofit.AlkemyAPIClient

class MiembrosDatarepository: IMiembrosDataRepository {
    override suspend fun getMiembros(): List<Miembros>? {
        val servicio = AlkemyAPIClient
            .getClient()
            .getDataMiembros()
            .body()
        return servicio?.data
    }

}