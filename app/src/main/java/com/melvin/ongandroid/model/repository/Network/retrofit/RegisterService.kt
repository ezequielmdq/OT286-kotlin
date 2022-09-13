package com.melvin.ongandroid.model.repository.Network.retrofit

import com.melvin.ongandroid.model.APIRegisterResponse
import com.melvin.ongandroid.model.Register
import com.melvin.ongandroid.model.data.RegisterData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class RegisterService @Inject constructor(private val alkemyAPIInterface: AlkemyAPIInterface) {
    suspend fun saveNewRegister(newRegister: Register): APIRegisterResponse<RegisterData, RegisterError>{
        val response = alkemyAPIInterface.sendDataRegistro(newRegister)
        response.let {
            return if (it.code() == 4) {
                val errorBody = Json.decodeFromString<RegisterError>(it.errorBody()!!.string())
                APIRegisterResponse(
                    success = false,
                    data = null,
                    error = errorBody,
                )
            } else{
                APIRegisterResponse(
                    success = true,
                    data = it.body(),
                    error = null
                )
            }
        }
    }

}