package com.melvin.ongandroid

import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.melvin.ongandroid.databinding.CardviewItemTestimonioBinding
import com.melvin.ongandroid.model.Testimonio

/**
 * enlaza el objeto "cardItemTestimonios" con el "testimonio" pasados como argumentos {
 */
fun bindTestimonio(cardItemTestimonios: CardviewItemTestimonioBinding, item: Testimonio){

    //paso el objeto Testimonio al dataBinding
    cardItemTestimonios.testimonio = item

    //descargo la imagen con Glide y la muestro en la ImageView
    val imageTestimonio = cardItemTestimonios.imageViewTestimonio
    Glide.with(imageTestimonio.context)
        .load(item.image)
        .into(imageTestimonio)
}


