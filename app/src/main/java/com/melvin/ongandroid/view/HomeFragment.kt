package com.melvin.ongandroid.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var novedadAdapter: NovedadAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iniciarRecyclerView(view)

        crearYCargarLista()

    }

    //Configuracion del Recycler view
    private fun iniciarRecyclerView(view: View) {
        novedadAdapter = NovedadAdapter()

        binding.rvNovedades.apply {

            adapter = novedadAdapter

        }
    }

    //Carga las listas con imagenes random
    private fun crearYCargarLista() {
        val novedades = listOf(
            Novedad("Novedad 1", "https://loremflickr.com/320/240", "descripcion 1"),
            Novedad("Novedad 2", "https://loremflickr.com/320/240/dog", "descripcion 2"),
            Novedad("Novedad 3", "https://loremflickr.com/g/320/240/paris", "descripcion 3"),
            Novedad("Novedad 4", "https://loremflickr.com/g/320/240/roma", "descripcion 4"))

        novedadAdapter.actualizarData(novedades)

    }




}