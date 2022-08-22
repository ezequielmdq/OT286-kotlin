package com.melvin.ongandroid.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.melvin.ongandroid.databinding.FragmentHomeBinding
import com.melvin.ongandroid.model.Novedad
import com.melvin.ongandroid.model.WelcomeImage

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var novedadAdapter : NovedadAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        configurarListaBienvenida()
        configObservers()

        iniciarRecyclerViewNovedades()
        crearYCargarListaNovedades()

        return binding.root
    }

    private fun configObservers() {
    }

    private fun configurarListaBienvenida(){
        val list = listOf(
            WelcomeImage("https://loremflickr.com/320/240", "Hola", "Como estan bla bla"),
            WelcomeImage("https://loremflickr.com/320/240/dog", "Hola", "Como estan bla bla"),
            WelcomeImage("https://loremflickr.com/g/320/240/paris", "Hola", "Como estan bla bla"),
            WelcomeImage("https://loremflickr.com/320/240/brazil,rio", "Hola", "Como estan bla bla"),
            WelcomeImage("https://loremflickr.com/g/320/240/paris,girl/all", "Hola", "Como estan bla bla")
        )

        val adapter = ListAdapter()
        //lista temporal
        adapter.list.addAll(list)
        binding.rv.adapter = adapter
    }

    //Configuracion del Recycler view
    private fun iniciarRecyclerViewNovedades() {
        novedadAdapter = NovedadAdapter()

        binding.rvNovedades.apply {

            adapter = novedadAdapter

        }
    }

    //Carga las listas con imagenes random
    private fun crearYCargarListaNovedades() {
        val novedades = listOf(
            Novedad("Novedad 1", "https://loremflickr.com/320/240", "descripcion 1"),
            Novedad("Novedad 2", "https://loremflickr.com/320/240/dog", "descripcion 2"),
            Novedad("Novedad 3", "https://loremflickr.com/g/320/240/paris", "descripcion 3"),
            Novedad("Novedad 4", "https://loremflickr.com/g/320/240/roma", "descripcion 4")
        )

        novedadAdapter.actualizarData(novedades)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

