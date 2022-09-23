package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.*
import com.melvin.ongandroid.businesslogic.FirebaseLog
import com.melvin.ongandroid.model.Miembros
import com.melvin.ongandroid.model.repository.Network.interfaces.IMiembrosDataRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch



class MiembrosViewModel( private val repositoryMiembros: IMiembrosDataRepository) : ViewModel() {

    private val _listaMiembros = MutableLiveData<List<Miembros>?>()
    val listaMiembros: LiveData<List<Miembros>?> = _listaMiembros

    //boolean observable que se setea en true cuando se esta consultando la api
    private val _isLoad: MutableLiveData<Boolean> = MutableLiveData()
    val isLoad: LiveData<Boolean> = _isLoad

    //observable que se cambiara a true si hay un error de carga
    private val _error = MutableLiveData<Boolean?>()
    val error: LiveData<Boolean?> = _error

    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.printStackTrace()
    }

    init {
        loadMiembros()
    }

    fun loadMiembros() {
        viewModelScope.launch(coroutineExceptionHandler) {
            try {

                val list = repositoryMiembros
                    .getMiembros()
                _listaMiembros.value = list
                _error.value = false

                /**se genera el log de evento de conexion exitosa*/
                FirebaseLog.logMiembrosSuccess()
                if (list.isNullOrEmpty()) {
                    _listaMiembros.value = emptyList()
                    _error.value = true
                } else {
                    _listaMiembros.value = list
                }

            } catch (e: Exception) {

                /**se genera el log de evento de error de conexion*/
                FirebaseLog.logMiembrosError()
                //seteo el observable error en true
                _error.value = true
                _listaMiembros.value = emptyList()

            }
        }
    }


}

class MiembrosViewModelFactory(
    private val repositoryMiembros: IMiembrosDataRepository
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MiembrosViewModel(repositoryMiembros) as T
    }

}


