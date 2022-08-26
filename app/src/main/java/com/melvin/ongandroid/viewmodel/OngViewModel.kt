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


    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        // handle thrown exceptions from coroutine scope
        throwable.printStackTrace()
    }

    init {
        loadSlide()
        loadTestimonios()
        loadNovedades()
    }

    //Carga slide
    private fun loadSlide() {
        viewModelScope.launch(coroutineExceptionHandler){
            try {
                val list = repositoryWelcomeImages.getWellcomeImages()
                _listaSlide.value = list
            }catch (e : Exception){
                _listaSlide.value = emptyList()
            }
        }
    }

    //Carga novedades
    private fun loadNovedades() {
        viewModelScope.launch(coroutineExceptionHandler) {
            try {
                val list = repositoryNovedades.getNovedades()
                _listaNovedad.value = list
            }catch (e : Exception){
                _listaNovedad.value = emptyList()
            }}
    }

    //carga testimonios
    private fun loadTestimonios() {
        viewModelScope.launch(coroutineExceptionHandler) {
            try {
                val list = repositoryTestimonios.getTestimonios()
                _listaTestimonios.value = list
            }catch (e : Exception){
                _listaTestimonios.value = emptyList()
            }}
    }

}
