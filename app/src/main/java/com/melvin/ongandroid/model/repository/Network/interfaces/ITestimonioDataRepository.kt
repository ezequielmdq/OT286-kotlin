package com.melvin.ongandroid.model.repository.Network.interfaces

import com.melvin.ongandroid.model.Testimonio

interface ITestimonioDataRepository {
    suspend fun getTestimonios(): List<Testimonio>?
}