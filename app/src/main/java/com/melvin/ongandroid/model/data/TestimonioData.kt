package com.melvin.ongandroid.model.data

import com.melvin.ongandroid.model.Testimonio

data class TestimonioData(val success : Boolean,
                          val data : List<Testimonio>,
                          val message : String
)