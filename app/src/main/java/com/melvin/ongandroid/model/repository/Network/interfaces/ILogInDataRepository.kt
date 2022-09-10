package com.melvin.ongandroid.model.repository.Network.interfaces

import com.melvin.ongandroid.model.LogIn
import com.melvin.ongandroid.model.data.LogInData

interface ILogInDataRepository {

    suspend fun postLogin(logIn : LogIn): LogInData?
}