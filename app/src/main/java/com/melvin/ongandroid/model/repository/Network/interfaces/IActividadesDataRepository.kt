package com.melvin.ongandroid.model.repository.Network.interfaces

import com.melvin.ongandroid.model.Actividad

interface IActividadesDataRepository {

    suspend fun getActividadesData(): List<Actividad>?

}
