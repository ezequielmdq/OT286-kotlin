package com.melvin.ongandroid.view.contacto


import android.view.View.*
import androidx.databinding.BindingAdapter
import com.google.android.material.progressindicator.CircularProgressIndicator


/** Adaptador binding que popne visible u oculta el spinner*/
@BindingAdapter("app:showSppinner")
    fun mostrarSpinner(view: CircularProgressIndicator, boolean : Boolean){
        if (boolean == true) view.visibility = VISIBLE
        else view.visibility = INVISIBLE
    }
