package com.melvin.ongandroid.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.melvin.ongandroid.R
import com.melvin.ongandroid.model.Novedad


class NovedadAdapter : RecyclerView.Adapter<NovedadAdapter.NovedadViewHolder>() {

    private var novedades = ArrayList<Novedad>()
    private lateinit var context : Context
    private lateinit var itemView: View

    inner class NovedadViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val ivNovedad = view.findViewById<ImageView>(R.id.iv_novedad)
        private val tvTitulo = view.findViewById<TextView>(R.id.tv_titulo)
        private val tvDescripcion = view.findViewById<TextView>(R.id.tv_descripcion)


        fun bind(novedad: Novedad, context: Context){

                tvTitulo.text = novedad.titulo
                tvDescripcion.text = novedad.descripcion
                Glide.with(context).load(novedad.imagen).into(ivNovedad)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NovedadViewHolder {
        context = parent.context

        itemView = if(viewType == R.layout.item_novedad){
            LayoutInflater.from(parent.context).inflate(R.layout.item_novedad, parent, false)

        }else{
            LayoutInflater.from(parent.context).inflate(R.layout.flecha, parent, false)

        }

        return NovedadViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: NovedadViewHolder, position: Int) {

        if(novedades == emptyList<Novedad>()){
            holder.itemView.isVisible = false
        }

        if(position == novedades.size){
            //aca iria la implementacion de la flecha que permite pasar a otra pantalla
        }else{
            val item = novedades[position]
            holder.bind(item, context)
        }

    }

    override fun getItemCount(): Int = novedades.size + 1

    override fun getItemViewType(position: Int): Int {
        return if(position == novedades.size){
            R.layout.flecha
        }else{
            R.layout.item_novedad
        }
    }

    //Recibe una lista y la carga en el recycler view
    fun actualizarData(data: List<Novedad>) {
        //if(data.size <= 4){
            novedades.clear()
            for (a in 0..3) {
                novedades.add(data[a])
                notifyDataSetChanged()
        }

    }


}