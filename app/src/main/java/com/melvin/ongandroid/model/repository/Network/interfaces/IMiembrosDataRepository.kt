package com.melvin.ongandroid.model.repository.Network.interfaces

import com.melvin.ongandroid.model.Miembros

interface IMiembrosDataRepository {
    suspend fun getMiembros(): List<Miembros>?
}