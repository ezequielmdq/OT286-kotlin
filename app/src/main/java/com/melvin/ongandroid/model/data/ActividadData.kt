package com.melvin.ongandroid.model.data

import com.melvin.ongandroid.model.Actividad

data class ActividadData(val success: Boolean,
                         val data: List<Actividad>,
                         val message: String)

