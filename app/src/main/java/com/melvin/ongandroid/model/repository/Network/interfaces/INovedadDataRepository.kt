package com.melvin.ongandroid.model.repository.Network.interfaces

import com.melvin.ongandroid.model.Novedad

interface INovedadDataRepository {
    suspend fun getNovedades(): List<Novedad>?
}