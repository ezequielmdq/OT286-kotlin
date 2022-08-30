package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.*
import com.melvin.ongandroid.model.Actividad
import com.melvin.ongandroid.model.repository.Network.interfaces.IActividadesDataRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class ActividadesViewModel(private val repositoryActividades: IActividadesDataRepository)
    : ViewModel(){

    //lista de actividades observable
    private val _actividades: MutableLiveData<List<Actividad>?> = MutableLiveData()
    val actividades: LiveData<List<Actividad>?> = _actividades

    //boolean observable que se setea en true cuando se esta consultando la api
    private val _isLoad: MutableLiveData<Boolean> = MutableLiveData()
    val isLoad: LiveData<Boolean> = _isLoad

    //observable de estado de error
    private val _error: MutableLiveData<Boolean> = MutableLiveData()
    val error: LiveData<Boolean> = _error

    private fun loadActividades(){
        viewModelScope.launch {
            try {
                val list = repositoryActividades.getActividadesData()
                _actividades.value = list
                //si la lista esta vacia muestro el error
                if(list?.isEmpty() == true){
                    showError()
                }
            }catch (e: Exception){
                showError()
            }
        }
    }

    private fun showError(){
        _error.value = true
    }

}

class ActividadViewModelFactory(
    private val repositoryActividades: IActividadesDataRepository
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ActividadesViewModel(repositoryActividades) as T
    }

}