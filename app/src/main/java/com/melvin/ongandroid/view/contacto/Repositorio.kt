package com.melvin.ongandroid.view.contacto

import com.melvin.ongandroid.model.Novedad
import com.melvin.ongandroid.model.repository.Network.retrofit.AlkemyAPIClient

class Repositorio {
    suspend fun setContacto(dto : ContactosDto): ContactosDto? {
        val servicio = AlkemyAPIClient
            .getClient()
            .setDataContacto(dto)
            .body()
        return servicio
    }

}