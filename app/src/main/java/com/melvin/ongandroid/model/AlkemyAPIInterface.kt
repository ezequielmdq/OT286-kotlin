package com.melvin.ongandroid.model

import com.melvin.ongandroid.model.data.NovedadData
import com.melvin.ongandroid.model.data.WelcomeData
import retrofit2.Response
import retrofit2.http.GET

interface AlkemyAPIInterface {
    @GET
    fun fetchData() { }

    @GET("slides")
    suspend fun getData() : Response<WelcomeData>

    @GET("news")
    suspend fun getDataNovedad() : Response<NovedadData>

}
