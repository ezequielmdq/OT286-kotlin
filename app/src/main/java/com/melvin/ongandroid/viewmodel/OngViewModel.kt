package com.melvin.ongandroid.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.businesslogic.FirebaseLog

import com.melvin.ongandroid.model.Miembros

import com.melvin.ongandroid.model.Novedad
import com.melvin.ongandroid.model.Testimonio
import com.melvin.ongandroid.model.WelcomeImage
import com.melvin.ongandroid.model.repository.Network.interfaces.IMiembrosDataRepository
import com.melvin.ongandroid.model.repository.Network.interfaces.INovedadDataRepository
import com.melvin.ongandroid.model.repository.Network.interfaces.ITestimonioDataRepository
import com.melvin.ongandroid.model.repository.Network.interfaces.IWelcomeDataRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class OngViewModel(private val repositoryWelcomeImages : IWelcomeDataRepository,
                   private val repositoryNovedades : INovedadDataRepository,
                   private val repositoryTestimonios : ITestimonioDataRepository,
                   private val repositoryMiembros: IMiembrosDataRepository
) : ViewModel() {

    private val errorTestimonio = MutableLiveData(false)
    private val errorNovedades = MutableLiveData(false)
    private val errorActividades = MutableLiveData(false)
    val errorMassiva = MutableLiveData(false)

    //lista de welcomeImages observable
    private val _listaSlide = MutableLiveData<List<WelcomeImage>?>()
    val listaSlide : LiveData<List<WelcomeImage>?> = _listaSlide

    //lista de novedades observable
    private val _listaNovedad = MutableLiveData<List<Novedad>?>()
    val listaNovedad : LiveData<List<Novedad>?> = _listaNovedad

    //lista de testimonios observable
    private val _listaTestimonios = MutableLiveData<List<Testimonio>?>()
    val listaTestimonios : LiveData<List<Testimonio>?> = _listaTestimonios

    //lista de miembros observable
    private val _listaMiembros = MutableLiveData<List<Miembros>?>()
    val listaMiembros : LiveData<List<Miembros>?> = _listaMiembros

    //observable que se cambiara a true si hay un error de carga
    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        // handle thrown exceptions from coroutine scope
        throwable.printStackTrace()
    }

    init {
       loadAll()
    }

    private fun loadAll(){
        loadSlide()
        loadTestimonios()
        loadNovedades()
        loadMiembros()
    }

    /**
     * hace la peticion de WelcomeImages con su repository correspondiente
     * en caso de error setea el liveData "_onErrorLoad" en true
     * para que los observadores se enteren del error
     */
     fun loadSlide() {
        viewModelScope.launch(coroutineExceptionHandler){
            try {
                val list = repositoryWelcomeImages.getWellcomeImages()
                _listaSlide.value = list
                /**se genera el log de evento de conexion exitosa*/
                FirebaseLog.logSliderSuccess()
                if(list.isNullOrEmpty()){
                    _listaSlide.value = emptyList()

                }else{
                    _listaSlide.value = list
                }
            }catch (e : Exception){
                /**se genera el log de evento de error de conexion*/
                FirebaseLog.logSliderError()
                //seteo el observable error en true
                _error.value = true
                _listaSlide.value = emptyList()

            }
        }
    }

    /**
     * hace la peticion de novedades con su repository correspondiente
     * en caso de error setea el liveData "_onErrorLoad" en true
     * para que los observadores se enteren del error
     */
     fun loadNovedades() {
        viewModelScope.launch(coroutineExceptionHandler) {
            try {
                val list = repositoryNovedades.getNovedades()
                 _listaNovedad.value = list
    
                /**se genera el log de evento de conexion exitosa*/
                FirebaseLog.logNovedadesSuccess()
                if(list.isNullOrEmpty()){
                    _listaNovedad.value = emptyList()
                }else{
                    _listaNovedad.value = list
                }
                
            }catch (e : Exception){
                /**se genera el log de evento de error de conexion*/
                FirebaseLog.logNovedadesError()
                //seteo el observable error en true
                _error.value = true
                _listaNovedad.value = emptyList()

            }}
    }

    /**
     * hace la peticion de tertimonios con su repository correspondiente
     * en caso de error setea el liveData "_onErrorLoad" en true
     * para que los observadores se enteren del error
     */
     fun loadTestimonios() {
        viewModelScope.launch(coroutineExceptionHandler) {
            try {
            
                val list = repositoryTestimonios
                    .getTestimonios()
                _listaTestimonios.value = list
                
                 /**se genera el log de evento de conexion exitosa*/
                FirebaseLog.logTestimonioSuccess()
                if(list.isNullOrEmpty()){
                    _listaTestimonios.value = emptyList()
                }else{
                    _listaTestimonios.value = list 
                }

            }catch (e : Exception){
                /**se genera el log de evento de error de conexion*/
                FirebaseLog.logTestimonioError()
                //seteo el observable error en true
                _error.value = true
                _listaTestimonios.value = emptyList()

            }}
    }

    /**
     * hace la peticion de miembros con su repository correspondiente
     * en caso de error setea el liveData "_onErrorLoad" en true
     * para que los observadores se enteren del error
     */
    fun loadMiembros() {
        viewModelScope.launch(coroutineExceptionHandler) {
            try {

                val list = repositoryMiembros
                    .getMiembros()
                _listaMiembros.value = list

                /**se genera el log de evento de conexion exitosa*/
                FirebaseLog.logMiembrosSuccess()
                if(list.isNullOrEmpty()){
                    _listaMiembros.value = emptyList()
                    _error.value = true
                }else{
                    _listaMiembros.value = list
                }

            }catch (e : Exception){
                /**se genera el log de evento de error de conexion*/
                FirebaseLog.logMiembrosError()
                //seteo el observable error en true
                _error.value = true
                _listaMiembros.value = emptyList()

            }}
    }
        // funcion a llamar cuando se falla los tres servicios
    fun checkErrorMassiva(){
        if(errorNovedades.value == true && errorActividades.value == true && errorTestimonio.value == true){
            errorMassiva.value = true
        }
    }

    /**
     * vuelve a intentar cargar todos los datos de los reposiorios
     */
    fun retry(){
        //lo hice temporalmente asi, no estoy muy seguro que decia el ticket de respuesta de error
        loadAll()
    }


}
