package com.melvin.ongandroid.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.model.Novedad
import com.melvin.ongandroid.model.data.NovedadDataRepositorio
import com.melvin.ongandroid.model.data.WelcomeDataRepositorio
import com.melvin.ongandroid.model.data.WelcomeImage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class OngViewModel(private val repositoryWelcome : WelcomeDataRepositorio, private val repositoryNovedad : NovedadDataRepositorio) : ViewModel() {

    private val _listaSlide = MutableLiveData<List<WelcomeImage>>()
    private val _listaNovedad = MutableLiveData<List<Novedad>>()

    val listaSlide : LiveData<List<WelcomeImage>> = _listaSlide
    val listaNovedad : LiveData<List<Novedad>> = _listaNovedad
    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        // handle thrown exceptions from coroutine scope
        throwable.printStackTrace()
    }

    //Carga slide

    fun loadSlide() {
        viewModelScope.launch(coroutineExceptionHandler){
            try {
                _listaSlide.value = repositoryWelcome.getDataSlide()
            }catch (e : Exception){
                _listaSlide.value = emptyList()
            }
        }
    }

    //Carga novedades

    fun loadNovedades() {
        viewModelScope.launch(coroutineExceptionHandler) {
            try {
                _listaNovedad.value = repositoryNovedad.getDataNovedad()
            }catch (e : Exception){
                _listaNovedad.value = emptyList()
            }}
    }
}
