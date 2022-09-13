package com.melvin.ongandroid.model.repository.Network.interfaces

import com.melvin.ongandroid.model.APIRegisterResponse
import com.melvin.ongandroid.model.Register
import com.melvin.ongandroid.model.data.RegisterData
import com.melvin.ongandroid.model.repository.Network.retrofit.RegisterError
import javax.inject.Inject

class NewRegisterStatus @Inject constructor(private val iRegisterDataRepository: IRegisterDataRepository) {
    suspend operator fun invoke(register: Register): APIRegisterResponse<RegisterData, RegisterError>{
        return iRegisterDataRepository.postRegister(Register())
    }
}