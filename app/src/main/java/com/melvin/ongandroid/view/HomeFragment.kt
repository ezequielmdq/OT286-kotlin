package com.melvin.ongandroid.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.melvin.ongandroid.R
//import com.melvin.ongandroid.bindTestimonio
import com.melvin.ongandroid.databinding.FragmentHomeBinding

import com.melvin.ongandroid.model.Novedad

import com.melvin.ongandroid.model.AlkemyAPIClient
import com.melvin.ongandroid.model.data.WelcomeImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var novedadAdapter : NovedadAdapter

    private var adapter = ListAdapter()
    private var dataslide = mutableListOf<WelcomeImage>()



    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.rv.adapter = adapter


        loadSlide()
        configLists()
        configObservers()
        iniciarRecyclerViewNovedades()
        crearYCargarListaNovedades()
        onLoadError(" ", { Unit })


        return binding.root

    }

    private fun configObservers() {

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


    // Llamado a servicio Retrofit


    fun loadSlide() {
        CoroutineScope(Dispatchers.Main).launch {
            val servicio = AlkemyAPIClient.getClient().getData().body()
            val data = servicio?.data
            dataslide.clear()
            if (data != null) {
                dataslide.addAll(data)
                adapter.loadDataSlide(dataslide)
            }
        }
    }



   private fun configLists(){


   }


    fun onLoadError(message: String, retryRecycler: () -> Unit) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
            .setAction(resources.getString(R.string.retry)) { retryRecycler() }
            .show()
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

