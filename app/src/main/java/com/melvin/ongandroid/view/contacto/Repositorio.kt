package com.melvin.ongandroid.view.contacto

import com.melvin.ongandroid.model.data.ContactosData
import com.melvin.ongandroid.model.repository.Network.retrofit.AlkemyAPIClient

class Repositorio {
    suspend fun setContacto(dto : ContactosDto): ContactosData? {
        val servicio = AlkemyAPIClient
            .getClient()
            .setDataContacto(dto)
            .body()
        return servicio
    }

}