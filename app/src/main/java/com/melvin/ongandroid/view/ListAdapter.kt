package com.melvin.ongandroid.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.CardviewBienvenidosBinding
import com.melvin.ongandroid.model.data.WelcomeImage

class ListAdapter: RecyclerView.Adapter<ListAdapter.WelcomeViewHolder>() {

     var list = mutableListOf<WelcomeImage>()

    // Carga repositorio slide
    fun loadDataSlide(data : List<WelcomeImage>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()

    }

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WelcomeViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = CardviewBienvenidosBinding.inflate(inflater, parent, false)
        return WelcomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WelcomeViewHolder, position: Int) {
        if (list == emptyList<WelcomeImage>()) {
            holder.itemView.isVisible = false

        }

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
                    .placeholder(R.drawable.loading_animation)
                    .into(imageView)
            }
        }

    }
}