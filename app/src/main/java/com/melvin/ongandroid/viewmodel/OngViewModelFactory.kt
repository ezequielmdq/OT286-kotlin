package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.melvin.ongandroid.model.repository.Network.interfaces.INovedadDataRepository
import com.melvin.ongandroid.model.repository.Network.interfaces.ITestimonioDataRepository
import com.melvin.ongandroid.model.repository.Network.interfaces.IWelcomeDataRepository

class OngViewModelFactory(private val repositoryWelcomeImages: IWelcomeDataRepository,
                          private val repotoryNovedades: INovedadDataRepository,
                          private val repositoryTestimonios: ITestimonioDataRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OngViewModel(
            repositoryWelcomeImages,
            repotoryNovedades,
            repositoryTestimonios) as T
    }


}