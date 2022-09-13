package com.melvin.ongandroid.view.newRegister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.FragmentRegisterBinding
import com.melvin.ongandroid.viewmodel.RegisterViewModel


class RegisterFragment : Fragment() {
    private lateinit var _binding: FragmentRegisterBinding
    private val binding get() = _binding
    private val viewModel: RegisterViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
           viewLifecycleOwner
        }

    }

    private fun registerListener(){
        binding.btnInicio.setOnClickListener {
            viewModel.saveNewRegister(binding.usernameTextEdit.text.toString(),
                binding.emailTextEdit.text.toString(),
                binding.passwordTextEdit.text.toString())
        }
    }

}