package com.melvin.ongandroid.model.repository.Network.implement

import com.melvin.ongandroid.model.Actividad
import com.melvin.ongandroid.model.repository.Network.interfaces.IActividadesDataRepository
import com.melvin.ongandroid.model.repository.Network.retrofit.AlkemyAPIClient

class ActividadesDataRepository: IActividadesDataRepository {
    override suspend fun getActividadesData(): List<Actividad>? {
        val actividadesData = AlkemyAPIClient.getClient().getDataActividades().body()
        return actividadesData?.data
    }
}