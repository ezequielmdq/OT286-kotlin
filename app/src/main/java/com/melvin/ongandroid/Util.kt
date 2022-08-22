package com.melvin.ongandroid

import com.bumptech.glide.Glide
import com.melvin.ongandroid.databinding.CardviewItemTestimonioBinding
import com.melvin.ongandroid.model.Testimonio

fun bindTestimonio(cardItemTestimonios: CardviewItemTestimonioBinding, item: Testimonio){
    cardItemTestimonios.testimonio = item
    val imageTestimonio = cardItemTestimonios.imageViewTestimonio
    Glide.with(imageTestimonio.context)
        .load(item.image)
        .into(imageTestimonio)
}