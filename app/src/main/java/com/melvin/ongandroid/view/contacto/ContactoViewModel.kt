package com.melvin.ongandroid.view.contacto


import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.model.data.ContactosData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.lang.Exception

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
    private val _spinnervisible = MutableLiveData<Boolean?>()
    val spinnervisible : LiveData<Boolean?> = _spinnervisible

    private val servicio = MutableLiveData<ContactosData?>()
    val _servicio : LiveData<ContactosData?> = servicio

    private val _isError: MutableLiveData<Boolean?> = MutableLiveData()
    val isError: LiveData<Boolean?> = _isError


    init {
        //seteo los observables en null para que no hagan nada los observadores
        _isError.value = null
        _spinnervisible.value = null
    }

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
            try {
                //muestro que que esta cargando
                onLoading()
                //hago la peticion post
                val result = Repositorio().setContacto(dto)
                //seteo el valor regresado
                servicio.value = result
                //miro si lo devuelto es null o si la variable success es false
                if((result == null) || !result.success){
                    //en ese caso hubo un error
                    onError()
                }else{
                    //si no
                    onSuccess()
                }
            }catch (e: Exception){
                //caso en el que hubo una excepcion
                onError()
            }
        }
    }

    /** avisa que no hubo error y que debe terminar de cargar*/
    private fun onSuccess(){
        _isError.value = false
        doneLoading()
    }

    /** avisa que hubo error y debe termina de cargar*/
    private fun onError(){
        _isError.value = true
        doneLoading()
    }

    /** setea el error en null para casos de que se recree la vista del fragment*/
    fun doneError(){
        _isError.value = null
    }

    /** avisa que el sppinner debe mostrarse*/
    private fun onLoading(){
        _spinnervisible.value = true
    }

    /** avisa que el sppinner debe ocultarse*/
    fun doneLoading(){
        _spinnervisible.value = false
    }

}