package com.melvin.ongandroid.model.repository.Network.interfaces

import com.melvin.ongandroid.model.Register
import com.melvin.ongandroid.model.data.RegisterData

interface IRegisterDataRepository {
    suspend fun postRegister(register : Register) : RegisterData?
}