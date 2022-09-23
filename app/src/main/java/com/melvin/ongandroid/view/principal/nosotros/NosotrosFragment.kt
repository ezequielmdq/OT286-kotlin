package com.melvin.ongandroid.view.Nosotros

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentNosotrosBinding
import com.melvin.ongandroid.model.Miembros

import com.melvin.ongandroid.model.Personal
import com.melvin.ongandroid.model.repository.Network.implement.LogInDataRepository
import com.melvin.ongandroid.model.repository.Network.implement.MiembrosDatarepository
import com.melvin.ongandroid.view.principal.nosotros.MiembroClickListener
import com.melvin.ongandroid.view.principal.nosotros.MiembroFragment
import com.melvin.ongandroid.viewmodel.LogInViewModel
import com.melvin.ongandroid.viewmodel.LogInViewModelFactory
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
                findNavController().navigate(R.id.action_nosotrosFragment_to_miembroFragment)
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

    private fun configList(list: List<Miembros>){
        adapterNosotros.list = list
        binding.apply {
            rvNosotros.adapter = adapterNosotros
            val grid = GridLayoutManager(context, 2)
            rvNosotros.layoutManager = grid
        }

    }

}