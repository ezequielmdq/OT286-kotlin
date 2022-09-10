package com.melvin.ongandroid.view.principal.nosotros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.melvin.ongandroid.databinding.MiembroFragmentBinding

class MiembroFragment : Fragment() {


    lateinit var binding : MiembroFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

    binding = MiembroFragmentBinding.inflate(inflater, container, false)



    return binding.root
    }
}