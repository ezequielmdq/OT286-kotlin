package com.melvin.ongandroid.view.Home

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
import com.melvin.ongandroid.removeTags
import com.melvin.ongandroid.view.principal.home.NovedadListener


class NovedadAdapter() : RecyclerView.Adapter<NovedadAdapter.NovedadViewHolder>() {

    var novedades = mutableListOf<Novedad>()
    private lateinit var context : Context
    private lateinit var itemView: View

    inner class NovedadViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val ivNovedad = view.findViewById<ImageView>(R.id.iv_novedad)
        private val tvTitulo = view.findViewById<TextView>(R.id.tv_titulo)
        private val tvDescripcion = view.findViewById<TextView>(R.id.tv_descripcion)



        fun bind(novedad: Novedad, context: Context){

                tvTitulo.text = novedad.titulo

                val descripcion = removeTags(novedad.descripcion)

                tvDescripcion.text = descripcion
                Glide.with(context).load(novedad.imagen).into(ivNovedad)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NovedadViewHolder {
        context = parent.context

        itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_novedad, parent, false)

            /*
            if(viewType == R.layout.item_novedad){
            } else{
            LayoutInflater.from(parent.context).inflate(R.layout.flecha, parent, false)

        }*/

        return NovedadViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: NovedadViewHolder, position: Int) {

        if(novedades == emptyList<Novedad>()){
            holder.itemView.isVisible = false
        }

        val item = novedades[position]
        holder.bind(item, context)

        /*if(position == novedades.size){
            //aca iria la implementacion de la flecha que permite pasar a otra pantalla
            holder.itemView.setOnClickListener{
                novedadListener.onFlechaClick()
            }
        }else{

        }*/

    }

    override fun getItemCount(): Int = novedades.size

    /*override fun getItemViewType(position: Int): Int {
        return if(position == novedades.size){
            R.layout.flecha
        }else{
            R.layout.item_novedad
        }
    }*/

    //Recibe una lista y la carga en el recycler view
    fun actualizarData(data: List<Novedad>) {

            novedades.clear()
            for (a in 0..3) {
                novedades.add(data[a])
                notifyDataSetChanged()
        }

    }


}