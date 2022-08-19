package com.melvin.ongandroid.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentHomeBinding
import com.melvin.ongandroid.model.WelcomeImage

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        configLists()
        configObservers()
        return binding.root
    }

    private fun configObservers() {
    }

    private fun configLists(){
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
