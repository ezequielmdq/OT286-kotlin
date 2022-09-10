package com.melvin.ongandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.melvin.ongandroid.model.repository.Network.interfaces.ILogInDataRepository

class LogInViewModelFactory(private val repositoryLogin : ILogInDataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LogInViewModel(repositoryLogin) as T
    }
}