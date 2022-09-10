package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.application.ONGApplication.Companion.prefs
import com.melvin.ongandroid.model.LogIn
import com.melvin.ongandroid.model.repository.Network.interfaces.ILogInDataRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class LogInViewModel(private val repositoryLogIn: ILogInDataRepository) : ViewModel() {

    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.printStackTrace()
    }

    private val _token = MutableLiveData<String?>()
    val token: LiveData<String?> = _token

//llama al metodo post desde una corrutina y guarda el valor en un livedata
    fun logIn(sesion : LogIn){

        viewModelScope.launch(coroutineExceptionHandler) {

            val response = repositoryLogIn.postLogin(sesion)

            _token.value = response?.data?.token

        }
    }

}