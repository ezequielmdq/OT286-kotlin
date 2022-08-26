package com.melvin.ongandroid.model.repository.Network.implement

import com.melvin.ongandroid.model.Novedad
import com.melvin.ongandroid.model.repository.Network.interfaces.INovedadDataRepository
import com.melvin.ongandroid.model.repository.Network.retrofit.AlkemyAPIClient

class NovedadDataRepository: INovedadDataRepository {

    override suspend fun getNovedades(): List<Novedad>? {
        val servicio = AlkemyAPIClient
            .getClient()
            .getDataNovedades()
            .body()
        return servicio?.data
    }

}