package com.melvin.ongandroid.view.principal.Novedades

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.melvin.ongandroid.R
import com.melvin.ongandroid.model.repository.Network.implement.MiembrosDatarepository
import com.melvin.ongandroid.model.repository.Network.implement.NovedadDataRepository
import com.melvin.ongandroid.model.repository.Network.implement.TestimonioDataRepository
import com.melvin.ongandroid.model.repository.Network.implement.WelcomeDataRepository
import com.melvin.ongandroid.view.Novedades.NovedadesAdapter
import com.melvin.ongandroid.viewmodel.OngViewModel
import com.melvin.ongandroid.viewmodel.OngViewModelFactory

class NovedadFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : NovedadesAdapter

    private val viewModel: OngViewModel by activityViewModels(
        factoryProducer = {
            OngViewModelFactory(
                WelcomeDataRepository(),
                NovedadDataRepository(),
                TestimonioDataRepository(),
                MiembrosDatarepository()
            )
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_novedad, container, false)
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rvNovedad)
        recyclerView.layoutManager = layoutManager
        adapter = NovedadesAdapter()
        adapter.novedades.addAll(emptyList())
        adapter.notifyDataSetChanged()
        cargarDatos()
        recyclerView.adapter = adapter

        return view
    }

    private fun dialogCartel() {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle("Error")
                .setMessage("Ha ocurrido un error obteniendo la información.")
                .setPositiveButton("Reintentar") { dialog, which ->
                    retry()
                }
                .setNeutralButton("Cancelar"){ dialog, which ->
                }
                .show()
        }
    }

    /** con este metodo se realiza la carga de datos en el recycler view*/
    private fun cargarDatos(){
        /** se consultan los datos*/
        viewModel.loadNovedades()
        /** se seciben los datos y se pasanal adapter como parametro*/
        val data = viewModel.listaNovedad.value
            if (data != null) {
                adapter.novedades.addAll(data)
                adapter.notifyDataSetChanged()
            }
        /** si la variable error es true se muestra el modal dialog de error*/
        val error = viewModel.error.value
        if(error == true) dialogCartel()}

    /**funcion reintentar*/
    private fun retry(){
        cargarDatos()
    }
}