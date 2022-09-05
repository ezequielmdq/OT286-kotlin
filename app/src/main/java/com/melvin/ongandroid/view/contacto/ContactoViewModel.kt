package com.melvin.ongandroid.view.contacto


import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class ContactoViewModel(private val dto: ContactosDto) : ViewModel(), Observable {
    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()
    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.printStackTrace()
    }

    /** variables bindeadas*/

    @Bindable
    private val nombre = MutableLiveData<String?>()
    val _nombre : MutableLiveData<String?> = nombre
    @Bindable
    private val email = MutableLiveData<String>()
    val _email : MutableLiveData<String> = email
    @Bindable
    private val mensaje = MutableLiveData<String>()
    val _mensaje : MutableLiveData<String> = mensaje
    @Bindable
    private val spinnervisible = MutableLiveData<Boolean>()
    val _spinnervisible : MutableLiveData<Boolean> = spinnervisible
    private val servicio = MutableLiveData<ContactosDto>()
    val _servicio : LiveData<ContactosDto> = servicio



    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }
    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }

    /** envio de datos*/

    fun enviarDatos(){
        viewModelScope.launch(coroutineExceptionHandler){
            dto.message = mensaje.value.toString()
            dto.name = nombre.value.toString()
            dto.email = email.value.toString()
            servicio.value = Repositorio().setContacto(dto)
        }

    }


}