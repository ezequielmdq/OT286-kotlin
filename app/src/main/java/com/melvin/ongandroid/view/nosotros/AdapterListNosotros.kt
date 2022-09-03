package com.melvin.ongandroid.view.Nosotros

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.CardPersonalBinding
import com.melvin.ongandroid.model.Personal

class AdapterListNosotros: RecyclerView.Adapter<AdapterListNosotros.PersonalHolder>() {

    private lateinit var context: Context
    var list: List<Personal>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = CardPersonalBinding.inflate(inflater, parent, false)
        return PersonalHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonalHolder, position: Int) {
        holder.bind(list?.get(position), context)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class PersonalHolder(private val binding: CardPersonalBinding)
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
        }
    }

}