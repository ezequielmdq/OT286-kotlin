package com.melvin.ongandroid.model.repository.Network.retrofit


import com.melvin.ongandroid.model.Novedad
import com.melvin.ongandroid.model.data.*
import com.melvin.ongandroid.model.Miembros
import retrofit2.Response
import retrofit2.http.GET

interface AlkemyAPIInterface {

    @GET("slides")
    suspend fun getDataWelcomeImages() : Response<WelcomeData>

    @GET("news")
    suspend fun getDataNovedades() : Response<NovedadData<List<Novedad>>>

    @GET("testimonials")
    suspend fun getDataTestimonios() : Response<TestimonioData>

    @GET("activities")
    suspend fun getDataActividades() : Response<ActividadData>

    @GET("members")
    suspend fun getDataMiembros() : Response<MiembrosData<List<Miembros>>>

}