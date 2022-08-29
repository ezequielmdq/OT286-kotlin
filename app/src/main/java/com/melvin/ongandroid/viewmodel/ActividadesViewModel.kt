package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.model.Actividad
import com.melvin.ongandroid.model.repository.Network.interfaces.IActividadesDataRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class ActividadesViewModel(val actividadesRepository: IActividadesDataRepository)
    : ViewModel(){

    //lista de actividades observable
    val _actividades: MutableLiveData<List<Actividad>?> = MutableLiveData()
    val actividades: LiveData<List<Actividad>?> = _actividades

    //boolean observable que se setea en true cuando se esta consultando la api
    val _isLoad: MutableLiveData<Boolean> = MutableLiveData()
    val isLoad: LiveData<Boolean> = _isLoad

    init {
        loadActividades()
    }

    fun loadActividades(){
        viewModelScope.launch {
            try {
                val list = actividadesRepository.getActividadesData()
                _actividades.value = list
            }catch (e: Exception){

            }
        }
    }

}