package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.model.Register
import com.melvin.ongandroid.model.repository.Network.interfaces.IRegisterDataRepository
import com.melvin.ongandroid.model.repository.Network.interfaces.NewRegisterStatus
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject
import javax.net.ssl.SSLEngineResult


class RegisterViewModel (private val newRegisterStatus: NewRegisterStatus) : ViewModel() {



    // button

    private val _buttonRegister = MutableLiveData(false)
    val buttonRegister: LiveData<Boolean> = _buttonRegister

    private val _errorMessageIsEnabled: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessageIsEnabled: LiveData<Boolean> = _errorMessageIsEnabled

    private val _statusNewRegister = MutableLiveData<Boolean>()
    val statusNewRegister : LiveData<Boolean> = _statusNewRegister



    fun validButtonRegister(name: String, email: String, password: String){
        _buttonRegister.postValue(name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty())
    }

// Para registrar usuario pero la funcion no está completo todavia
    // la parte errores y

    fun saveNewRegister(name: String, email: String, password: String){

        viewModelScope.launch {
            try{

                val responseRegister = newRegisterStatus(Register(name, email, password))
                if(responseRegister.success){
                    _statusNewRegister.postValue(true)

                }else{
                    _errorMessageIsEnabled.postValue(true)
                }
            }catch (e: Exception){
                _errorMessageIsEnabled.postValue(true)
            }

        }
    }

}

