package com.melvin.ongandroid.view.newRegister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
        _binding = FragmentRegisterBinding
            .inflate(inflater, container, false)

        configObservers()

        return binding.root
    }

    private fun configObservers() {
        viewModel.bottonEnable.observe(viewLifecycleOwner, Observer {
            if(it) {
                binding.apply {
                    btnInicio.setBackgroundColor(resources.getColor(R.color.red))
                    btnInicio.isEnabled = true
                }
            }
        })
        
        viewModel.passwordAreDiferent.observe(viewLifecycleOwner, Observer {
            if(it){
                binding.tvErrorContraseniasDistintas.visibility = View.VISIBLE
            }else{
                binding.tvErrorContraseniasDistintas.visibility = View.GONE
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
           viewLifecycleOwner
        }

    }

    private fun registerListener(){
        binding.btnInicio.setOnClickListener {
            viewModel.saveNewRegister(
                binding.username.text.toString(),
                binding.email.text.toString(),
                binding.password.text.toString())
        }

        binding.confirmPassword.text.toString()

    }



}