package com.melvin.ongandroid.view.Nosotros

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.melvin.ongandroid.databinding.FragmentNosotrosBinding
import com.melvin.ongandroid.model.Miembros

import com.melvin.ongandroid.model.repository.Network.implement.MiembrosDatarepository
import com.melvin.ongandroid.view.principal.nosotros.MiembroClickListener
import com.melvin.ongandroid.view.principal.nosotros.MiembroDialogo
import com.melvin.ongandroid.viewmodel.MiembrosViewModel
import com.melvin.ongandroid.viewmodel.MiembrosViewModelFactory

/**
 *
 */
class NosotrosFragment : Fragment() {

    lateinit var binding: FragmentNosotrosBinding
    private val viewModel : MiembrosViewModel by activityViewModels(
        factoryProducer = {
            MiembrosViewModelFactory(MiembrosDatarepository())
        }
    )
    private lateinit var adapterNosotros : AdapterListNosotros


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNosotrosBinding.inflate(inflater, container, false)
        adapterNosotros = AdapterListNosotros()
        adapterNosotros.setOnclicklistener(object : MiembroClickListener{
            override fun itemClick(position: Int) {
                /** Se capturan los datos de la api y se envian al dialog fragment*/
                val resultImagen = viewModel.listaMiembros.value?.get(position)?.image
                setFragmentResult("requestKey1", bundleOf("bundleKey1" to resultImagen))
                val resultNombre = viewModel.listaMiembros.value?.get(position)?.name
                setFragmentResult("requestKey2", bundleOf("bundleKey2" to resultNombre))
                val resultDescripcion = viewModel.listaMiembros.value?.get(position)?.description
                setFragmentResult("requestKey3", bundleOf("bundleKey3" to resultDescripcion))
                val resultUrlFace = viewModel.listaMiembros.value?.get(position)?.facebookUrl
                setFragmentResult("requestKey4", bundleOf("bundleKey4" to resultUrlFace))
                val resultUrlLi = viewModel.listaMiembros.value?.get(position)?.linkedinUrl
                setFragmentResult("requestKey5", bundleOf("bundleKey5" to resultUrlLi))
                getFragmentManager()?.let { MiembroDialogo().show(it, "DialogFragment") }
            }
        })

        configObservers()
        return binding.root
    }

    private fun configObservers() {
        viewModel.listaMiembros.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                configList(list)
            }


        })
    }

    private fun configList(list: List<Miembros>) {
        adapterNosotros.list = list
        binding.apply {
            rvNosotros.adapter = adapterNosotros
            val grid = GridLayoutManager(context, 2)
            rvNosotros.layoutManager = grid
        }

    }

}