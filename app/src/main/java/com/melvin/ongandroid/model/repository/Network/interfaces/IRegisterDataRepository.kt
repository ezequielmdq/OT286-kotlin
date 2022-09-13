package com.melvin.ongandroid.model.repository.Network.interfaces

import com.melvin.ongandroid.model.APIRegisterResponse
import com.melvin.ongandroid.model.Register
import com.melvin.ongandroid.model.data.RegisterData
import com.melvin.ongandroid.model.repository.Network.retrofit.RegisterError
import com.melvin.ongandroid.model.repository.Network.retrofit.RegisterService
import javax.inject.Inject

class IRegisterDataRepository @Inject constructor(private val registerService: RegisterService){
    suspend fun postRegister(register : Register) : APIRegisterResponse<RegisterData, RegisterError>{
        return registerService.saveNewRegister(register)
    }
}