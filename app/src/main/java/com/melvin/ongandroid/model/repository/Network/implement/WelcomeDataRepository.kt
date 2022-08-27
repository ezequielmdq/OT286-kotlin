package com.melvin.ongandroid.model.repository.Network.implement

import com.melvin.ongandroid.model.WelcomeImage
import com.melvin.ongandroid.model.repository.Network.interfaces.IWelcomeDataRepository
import com.melvin.ongandroid.model.repository.Network.retrofit.AlkemyAPIClient

class WelcomeDataRepository: IWelcomeDataRepository {

    //Repositorio slide
    override suspend fun getWellcomeImages(): List<WelcomeImage>? {
        val servicio = AlkemyAPIClient
            .getClient()
            .getDataWelcomeImages()
            .body()
        return servicio?.data
    }
}


