package com.melvin.ongandroid.view.newRegister

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.melvin.ongandroid.R
import com.melvin.ongandroid.businesslogic.FirebaseLog
import com.melvin.ongandroid.databinding.FragmentRegisterBinding
import com.melvin.ongandroid.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class RegisterFragment : Fragment() {
   
    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var _binding: FragmentRegisterBinding
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding
            .inflate(inflater, container, false)

        configObservers()
        registerListener()
        setearTextWatcher()

        return binding.root
    }

    private fun configObservers() {

        viewModel.errorMessageIsEnabled.observe(viewLifecycleOwner, Observer{ error ->
            if (error){
                showDialog("Ha ocurrido un error obteniendo la informacion")
                binding.username.error = "*campo obligatorio"
                binding.email.error = "*campo obligatorio"
                binding.password.error = "*campo obligatorio"
            }
        })

        //confiogro un observador para el boton
        viewModel.bottonEnable.observe(viewLifecycleOwner, Observer {enable ->
            val color = if(enable) {
                R.color.red
            }else {
                R.color.botom_disable
            }
            binding.apply {
                btnInicio.setBackgroundColor(resources.getColor(color))
                btnInicio.isEnabled = enable
            }
        })
        //confiogro un observador para el mensaje de contraseñas diferentes
        viewModel.passwordAreDiferent.observe(viewLifecycleOwner, Observer { areDiferent ->
            val visibility = if(areDiferent){
                View.VISIBLE
            } else {
                View.GONE
            }
            binding.tvErrorContraseniasDistintas.visibility = visibility
        })


    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.username.error = null
            binding.email.error = null
            binding.password.error = null
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }

    private fun setearTextWatcher() {
        binding.username.addTextChangedListener(textWatcher)
        binding.email.addTextChangedListener(textWatcher)
        binding.password.addTextChangedListener(textWatcher)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
           viewLifecycleOwner
        }

    }

    private fun registerListener(){
        binding.btnInicio.setOnClickListener {

            FirebaseLog.logSignUpClick()
            viewModel.saveNewRegister(binding.username.text.toString(),
                binding.email.text.toString(),
                binding.password.text.toString())

        }

        binding.confirmPassword.text.toString()

    }



    //Muestra el cuadro de dialogo al aparecer un error obteniendo informacion de la api
    private fun showDialog(message: String) {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("Aceptar") { dialog, which ->

                }
                .show()
        }
    }

}