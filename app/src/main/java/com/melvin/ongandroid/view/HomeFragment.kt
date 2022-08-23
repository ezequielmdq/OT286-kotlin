package com.melvin.ongandroid.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.databinding.FragmentHomeBinding

import com.melvin.ongandroid.model.data.Novedad

import com.melvin.ongandroid.model.data.WelcomeImage
import com.melvin.ongandroid.viewmodel.OngViewModel
import com.melvin.ongandroid.viewmodel.OngViewModelFactory


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var novedadAdapter : NovedadAdapter

    private var adapter = ListAdapter()


    private val viewModel : OngViewModel by activityViewModels(
        factoryProducer = {OngViewModelFactory()}
    )



    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)





        configLists()
        configObservers()
        iniciarRecyclerViewNovedades()
        crearYCargarListaNovedades()


        return binding.root

    }

    private fun configObservers() {


        viewModel.listaSlide.observe(viewLifecycleOwner){
            viewModel.listaSlide.value?.let { it1 -> adapter.loadDataSlide(it1) }
        }

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
      //  val novedades = listOf(
     //       Novedad("Novedad 1", "https://loremflickr.com/320/240", "descripcion 1"),
     //       Novedad("Novedad 2", "https://loremflickr.com/320/240/dog", "descripcion 2"),
     //       Novedad("Novedad 3", "https://loremflickr.com/g/320/240/paris", "descripcion 3"),
     //       Novedad("Novedad 4", "https://loremflickr.com/g/320/240/roma", "descripcion 4")
     //   )
        viewModel.loadNovedades()

        viewModel.listaNovedad.observe(viewLifecycleOwner){
            viewModel.listaNovedad.value?.let { it1 -> novedadAdapter.actualizarData(it1) }
        }



    }
    

   private fun configLists(){

       viewModel.loadSlide()
       binding.rv.adapter = adapter

   }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

