package com.melvin.ongandroid.view.principal.Actividades

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentActividadBinding
import com.melvin.ongandroid.model.Actividad
import com.melvin.ongandroid.model.repository.Network.implement.ActividadesDataRepository
import com.melvin.ongandroid.viewmodel.ActividadViewModelFactory
import com.melvin.ongandroid.viewmodel.ActividadesViewModel


class ActividadFragment : Fragment() {

    private lateinit var binding : FragmentActividadBinding

    private lateinit var actividadAdapter: ActividadAdapter

    private val viewModel: ActividadesViewModel by activityViewModels(
        factoryProducer = {
            ActividadViewModelFactory(ActividadesDataRepository())
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentActividadBinding.inflate(inflater, container, false)

        configObserver()

        //viewModel.loadActividades()

        return binding.root

    }

    private fun configObserver() {
        viewModel.apply {
            actividades.observe(viewLifecycleOwner, Observer{ listaActividades ->
                listaActividades?.let {
                    configActividades(listaActividades)
                }

            })

            error.observe(viewLifecycleOwner, Observer { error ->
                onLoadError()
                viewModel.doneError()
            })
        }
    }

    private fun onLoadError() {
        binding?.let {
            Snackbar
                .make(it.root, resources.getString(R.string.retry), Snackbar.LENGTH_INDEFINITE)
                .setAction("Reintentar") {

                    viewModel.loadActividades()
                }
                .show()
        }
    }

    private fun configActividades(actividades: List<Actividad>) {
        actividadAdapter = ActividadAdapter()
        actividadAdapter.actividades.addAll(actividades)
        actividadAdapter.notifyDataSetChanged()

        binding.rvActividad.adapter = actividadAdapter

    }


}