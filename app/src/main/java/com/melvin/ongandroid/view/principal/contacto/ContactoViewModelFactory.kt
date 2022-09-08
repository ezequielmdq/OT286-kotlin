package com.melvin.ongandroid.view.principal.contacto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ContactoViewModelFactory(private val dto : ContactosDto) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ContactoViewModel(dto) as T
        }
}
