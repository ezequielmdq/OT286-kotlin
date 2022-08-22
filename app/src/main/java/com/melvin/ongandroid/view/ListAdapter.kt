package com.melvin.ongandroid.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.melvin.ongandroid.databinding.CardviewBienvenidosBinding
import com.melvin.ongandroid.model.WelcomeImage

class ListAdapter: RecyclerView.Adapter<ListAdapter.WelcomeViewHolder>() {

    val list = mutableListOf<WelcomeImage>()
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WelcomeViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = CardviewBienvenidosBinding.inflate(inflater, parent, false)
        return WelcomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WelcomeViewHolder, position: Int) {
        holder.bind(list[position], context)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class WelcomeViewHolder(private val binding: CardviewBienvenidosBinding):
        RecyclerView.ViewHolder(binding.root) {

        /**
         * enlaza el objeto WelcomeImage pasado como argumento con este Holder
         */
        fun bind(item: WelcomeImage, context: Context){

            binding.apply {
                welcomeImage = item
                Glide.with(context)
                    .load(item.image)
                    .into(imageView)
            }
        }

    }
}