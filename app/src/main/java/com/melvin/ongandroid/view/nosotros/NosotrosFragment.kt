package com.melvin.ongandroid.view.Nosotros

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.melvin.ongandroid.R
import com.melvin.ongandroid.bindTestimonio
import com.melvin.ongandroid.databinding.FragmentNosotrosBinding

import com.melvin.ongandroid.model.Personal

/**
 *
 */
class NosotrosFragment : Fragment() {

    lateinit var binding: FragmentNosotrosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNosotrosBinding.inflate(inflater, container, false)

        configList()
        configObservers()

        return binding.root
    }

    private fun configObservers() {
    }

    private fun configList(){
        val list = listOf(
            Personal("https://picsum.photos/200/300", "Hola", "Desarrollador"),
            Personal("https://picsum.photos/seed/picsum/200/300", "Hello", "Dlbañil"),
            Personal("https://picsum.photos/200/300?grayscale", "Hi", "Vago"),
            Personal("https://picsum.photos/id/237/200/300", "Hi", "Vago"),
            Personal("https://picsum.photos/200/300", "Hi", "Vago")
        )

        val adapterNosotros = AdapterListNosotros()
        adapterNosotros.list = list
        binding.apply {
            rvNosotros.adapter = adapterNosotros
            rvNosotros.layoutManager = GridLayoutManager(context, 3)
        }

    }

}