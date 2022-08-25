package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.melvin.ongandroid.model.data.NovedadDataRepositorio
import com.melvin.ongandroid.model.data.WelcomeDataRepositorio

class OngViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {


        val repositoryWelcome = WelcomeDataRepositorio()
        val repositoryNovedad = NovedadDataRepositorio()
        return OngViewModel(repositoryWelcome, repositoryNovedad) as T
    }


}