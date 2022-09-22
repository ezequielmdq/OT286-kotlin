package com.melvin.ongandroid.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.application.ONGApplication.Companion.prefs
import com.melvin.ongandroid.application.Prefs
import com.melvin.ongandroid.application.Validator
import com.melvin.ongandroid.model.LogIn
import com.melvin.ongandroid.model.data.LogInData
import com.melvin.ongandroid.model.repository.Network.interfaces.ILogInDataRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

data class TextInputError<T>( val input: T, val validationTest: Boolean, val messageFeedback: String? = "")
enum class Inputype{Email, Password}

class LogInViewModel(private val repositoryLogIn: ILogInDataRepository) : ViewModel() {

    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.printStackTrace()
    }

    private val _token = MutableLiveData<String?>()
    val token: LiveData<String?> = _token

    private var emailEnter: Boolean = false
    private var passwordEnter: Boolean = false

    private val _loginError = MutableLiveData<TextInputError<Inputype>>()
    val loginError: LiveData<TextInputError<Inputype>> = _loginError

    private val _buttonLogin = MutableLiveData(false)
    val buttonLogin: LiveData<Boolean> = _buttonLogin


    // validar campos de email y password login

    fun validarCamposLogin(email: String, password: String, context: Context){
        viewModelScope.launch {
          val  loginResponse = repositoryLogIn.postLogin(LogIn(email, password))
            if(loginResponse!!.success){
                val logPrefs = Prefs(context)
                val token = repositoryLogIn.postLogin(LogIn(email,password))?.data?.token?:""
                logPrefs.saveToken(token)
            } else {
                val message = "Email o Password is not matching"
               _loginError.postValue(TextInputError(Inputype.Password, true, message))
            }

        }

    }

    fun inputLoginChange(inputText: String, inputType: Inputype){
      // val emailType = inputType.email
        when(inputType){
            Inputype.Email -> {
                val checkValidation = Validator.isEmailValid(inputText)
                val msg = if(checkValidation) "" else "Email is not matching"
                emailEnter = checkValidation
                _loginError.postValue(TextInputError(Inputype.Email, !checkValidation, msg))
            }
            Inputype.Password -> {
                val checkValidation = Validator.isPasswordValid(inputText)
                val msg = if(checkValidation) "" else "Passowrd is not matching"
                passwordEnter = checkValidation
                _loginError.postValue(TextInputError(Inputype.Password, !checkValidation, msg))
            }

        }
        _buttonLogin.postValue(emailEnter && passwordEnter)
    }


    //llama al metodo post desde una corrutina y guarda el valor en un livedata
    fun logIn(sesion : LogIn){

        viewModelScope.launch(coroutineExceptionHandler) {

            val response = repositoryLogIn.postLogin(sesion)

            _token.value = response?.data?.token

        }
    }

}