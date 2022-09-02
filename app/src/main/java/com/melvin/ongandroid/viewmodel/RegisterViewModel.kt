package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.model.repository.Network.interfaces.IRegisterDataRepository
import kotlinx.coroutines.launch

enum class RegisterType{NAME, EMAIL, PASSWORD, CONFIRM_PASSWORD}
data class RegisterError<T>(val input: T, val isValid: Boolean, val message: String = "")

class RegisterViewModel(private val registerRepository: IRegisterDataRepository) : ViewModel() {


    // button

    private val _buttonRegister = MutableLiveData(false)
    val buttonRegister: LiveData<Boolean> = _buttonRegister

        //  input
    private var _nameRegisterInput = false



    fun saveNewRegister(name: String, email: String, password: String){
        viewModelScope.launch {

        }
    }

}