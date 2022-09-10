package com.melvin.ongandroid.model.repository.Network.implement

import com.melvin.ongandroid.model.LogIn
import com.melvin.ongandroid.model.data.LogInData
import com.melvin.ongandroid.model.repository.Network.interfaces.ILogInDataRepository
import com.melvin.ongandroid.model.repository.Network.retrofit.AlkemyAPIClient

class LogInDataRepository : ILogInDataRepository {
    override suspend fun postLogin(logIn: LogIn): LogInData? {
        val servicio = AlkemyAPIClient
            .getClient()
            .setDataLogin(logIn)
            .body()
        return servicio
    }

}