package com.melvin.ongandroid.model

import retrofit2.http.GET

interface AlkemyAPIInterface {
    @GET
    fun fetchData() { }
}
