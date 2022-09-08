package com.melvin.ongandroid.view.Nosotros

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.CardPersonalBinding
import com.melvin.ongandroid.model.Personal
import com.melvin.ongandroid.view.principal.nosotros.MiembroClickListener

class AdapterListNosotros: RecyclerView.Adapter<AdapterListNosotros.PersonalHolder>(), MiembroClickListener {

    private lateinit var context: Context
    var list: List<Personal>? = null
    private lateinit var listener : MiembroClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = CardPersonalBinding.inflate(inflater, parent, false)
        return PersonalHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: PersonalHolder, position: Int) {
        holder.bind(list?.get(position), context)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class PersonalHolder(private val binding: CardPersonalBinding, val listener: MiembroClickListener)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Personal?, context: Context){
            item?.let {
                binding.apply {
                    Glide.with(context)
                        .load(item.image)
                        .placeholder(R.drawable.loading_animation)
                        .into(circleImageView)

                    personal = item
                }
            }
            binding.circleImageView.setOnClickListener{
                listener.itemClick(adapterPosition)
            }
        }
    }

    fun setOnclicklistener(listener : MiembroClickListener){
        this.listener = listener
    }

    override fun itemClick(position: Int) {
        TODO("Not yet implemented")
    }

}