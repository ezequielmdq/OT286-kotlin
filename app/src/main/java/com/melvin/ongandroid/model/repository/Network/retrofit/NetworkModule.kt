package com.melvin.ongandroid.model.repository.Network.retrofit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object NetworkModule {

    //get retrofit
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{

            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url
                    .newBuilder()
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getClient(): AlkemyAPIInterface {
        return getClient
    }
    private val getClient: AlkemyAPIInterface by lazy {
        AlkemyAPIClient.getRetrofit().create(AlkemyAPIInterface::class.java)
    }

    @Singleton
    @Provides

    fun provideAlkemyApiInterface(retrofit: Retrofit): AlkemyAPIInterface{
        return retrofit.create(AlkemyAPIInterface::class.java)
    }


}
