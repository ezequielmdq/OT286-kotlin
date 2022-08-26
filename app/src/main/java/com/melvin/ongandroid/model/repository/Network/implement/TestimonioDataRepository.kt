package com.melvin.ongandroid.model.repository.Network.implement

import com.melvin.ongandroid.model.Testimonio

import com.melvin.ongandroid.model.repository.Network.interfaces.ITestimonioDataRepository
import com.melvin.ongandroid.model.repository.Network.retrofit.AlkemyAPIClient

class TestimonioDataRepository: ITestimonioDataRepository {

    override suspend fun getTestimonios(): List<Testimonio>? {
        val servicio = AlkemyAPIClient
            .getClient()
            .getDataTestimonios()
            .body()
        return servicio?.data
    }
}