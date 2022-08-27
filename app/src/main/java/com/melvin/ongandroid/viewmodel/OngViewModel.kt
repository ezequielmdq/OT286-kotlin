package com.melvin.ongandroid.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.model.Novedad
import com.melvin.ongandroid.model.Testimonio
import com.melvin.ongandroid.model.WelcomeImage
import com.melvin.ongandroid.model.repository.Network.interfaces.INovedadDataRepository
import com.melvin.ongandroid.model.repository.Network.interfaces.ITestimonioDataRepository
import com.melvin.ongandroid.model.repository.Network.interfaces.IWelcomeDataRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class OngViewModel(private val repositoryWelcomeImages : IWelcomeDataRepository,
                   private val repositoryNovedades : INovedadDataRepository,
                   private val repositoryTestimonios : ITestimonioDataRepository) : ViewModel() {

    //lista de welcomeImages observable
    private val _listaSlide = MutableLiveData<List<WelcomeImage>?>()
    val listaSlide : LiveData<List<WelcomeImage>?> = _listaSlide

    //lista de novedades observable
    private val _listaNovedad = MutableLiveData<List<Novedad>?>()
    val listaNovedad : LiveData<List<Novedad>?> = _listaNovedad

    //lista de testimonios observable
    private val _listaTestimonios = MutableLiveData<List<Testimonio>?>()
    val listaTestimonios : LiveData<List<Testimonio>?> = _listaTestimonios

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

                if(list.isNullOrEmpty()){
                    _listaSlide.value = emptyList()
                    _error.value = true
                }else{
                    _listaSlide.value = list
                }


            }catch (e : Exception){
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

                if(list.isNullOrEmpty()){
                    _listaNovedad.value = emptyList()
                    _error.value = true
                }else{
                    _listaNovedad.value = list
                }


            }catch (e : Exception){
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
                val list = repositoryTestimonios.getTestimonios()

                if(list.isNullOrEmpty()){
                    _listaTestimonios.value = emptyList()
                    _error.value = true
                }else{
                _listaTestimonios.value = list
                }
            }catch (e : Exception){
                //seteo el observable error en true
                _error.value = true
                _listaTestimonios.value = emptyList()
            }}
    }

    /**
     * vuelve a intentar cargar todos los datos de los reposiorios
     */
    fun retry(){
        //lo hice temporalmente asi, no estoy muy seguro que decia el ticket de respuesta de error
        loadAll()
    }
}
