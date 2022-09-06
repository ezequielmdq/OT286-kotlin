package com.melvin.ongandroid.model.repository.Network.retrofit


import com.melvin.ongandroid.model.Novedad

import com.melvin.ongandroid.model.data.ActividadData
import com.melvin.ongandroid.model.data.NovedadData
import com.melvin.ongandroid.model.data.TestimonioData
import com.melvin.ongandroid.model.data.WelcomeData
import com.melvin.ongandroid.view.contacto.ContactosDto
import com.melvin.ongandroid.model.data.MiembrosData
import com.melvin.ongandroid.model.Miembros
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

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

    @POST("contacts")
    suspend fun setDataContacto(@Body dto : ContactosDto) : Response<ContactosDto>


}