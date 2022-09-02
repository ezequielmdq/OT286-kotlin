package com.melvin.ongandroid.model.repository.Network.implement

import com.melvin.ongandroid.model.Register
import com.melvin.ongandroid.model.data.RegisterData
import com.melvin.ongandroid.model.repository.Network.interfaces.IRegisterDataRepository
import com.melvin.ongandroid.model.repository.Network.retrofit.AlkemyAPIClient

class RegisterDataRepository : IRegisterDataRepository {
   override suspend fun postRegister(register: Register): RegisterData? {
       val servicio = AlkemyAPIClient.getClient().sendDataRegistro(Register()).body()
       return servicio
   }
}