package com.melvin.ongandroid.model

data class Actividad(val id: Int,
                     val name: String,
                     val slug: String,
                     val description: String,
                     val image: String,
                     val user_id: Int,
                     val category_id: String,
                     val created_at: String,
                     val updated_at: String,
                     val deleted_at: String,
                     val group_id: String
                     )