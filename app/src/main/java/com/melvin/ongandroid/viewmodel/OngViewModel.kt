package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.model.data.*
import kotlinx.coroutines.launch

class OngViewModel(private val repositoryWelcome : WelcomeDataRepositorio, private val repositoryNovedad : NovedadDataRepositorio) : ViewModel() {

        private val _listaSlide = MutableLiveData<List<WelcomeImage>>()
        private val _listaNovedad = MutableLiveData<List<Novedad>>()

    val listaSlide : LiveData<List<WelcomeImage>> = _listaSlide
    val listaNovedad : LiveData<List<Novedad>> = _listaNovedad

    fun loadSlide() {
        viewModelScope.launch {
            _listaSlide.value = repositoryWelcome.getDataSlide()
        }
    }
    fun loadNovedades(){
        viewModelScope.launch{
            _listaNovedad.value = repositoryNovedad.getDataNovedad()

        }

    }







}