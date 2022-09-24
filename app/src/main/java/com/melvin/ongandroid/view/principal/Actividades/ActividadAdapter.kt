package com.melvin.ongandroid.view.principal.Actividades

import android.content.Context
import android.view.LayoutInflater

import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.melvin.ongandroid.databinding.ItemActividadBinding
import com.melvin.ongandroid.model.Actividad
import com.melvin.ongandroid.removeTags


class ActividadAdapter: RecyclerView.Adapter<ActividadAdapter.ActividadViewHolder>() {

    var actividades = mutableListOf<Actividad>()
    lateinit var context : Context


    class ActividadViewHolder(private val binding: ItemActividadBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(actividad: Actividad, context: Context){

            binding.apply {
                tvNombre.text = actividad.name

                tvNameDescripcion.text = removeTags(actividad.description)
                Glide.with(context).load(actividad.image).into(ivFoto)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActividadViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemActividadBinding.inflate(inflater, parent, false)
        return ActividadViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActividadViewHolder, position: Int) {
        val item = actividades[position]
        holder.bind(item, context)
    }

    override fun getItemCount(): Int {
        return actividades.size
    }

}