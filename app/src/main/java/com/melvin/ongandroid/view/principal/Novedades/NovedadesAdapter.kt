package com.melvin.ongandroid.view.Novedades

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.melvin.ongandroid.R
import com.melvin.ongandroid.model.Novedad

class NovedadesAdapter : RecyclerView.Adapter<NovedadesAdapter.NovedadesViewHolder>() {

    var novedades = mutableListOf<Novedad>()
    lateinit var context : Context
    private lateinit var itemView: View

    class NovedadesViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val ivNovedad = view.findViewById<ImageView>(R.id.iv_novedad)
        private val tvTitulo = view.findViewById<TextView>(R.id.tv_titulo)
        private val tvDescripcion = view.findViewById<TextView>(R.id.tv_descripcion)

        fun bind(novedad: Novedad, context: Context){

            tvTitulo.text = novedad.titulo
            tvDescripcion.text = novedad.descripcion
            Glide.with(context).load(novedad.imagen).into(ivNovedad)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NovedadesViewHolder {
        context = parent.context
        itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_novedad, parent, false)
        return NovedadesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NovedadesViewHolder, position: Int) {
        val item = novedades[position]
        holder.bind(item, context)
    }

    override fun getItemCount(): Int {
        return novedades.size
    }



}