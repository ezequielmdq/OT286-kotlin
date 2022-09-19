package com.melvin.ongandroid.view.newRegister

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.melvin.ongandroid.R
import com.melvin.ongandroid.businesslogic.FirebaseLog
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

        registerListener()
        setearTextWatcher()
        configObservers()

        return binding.root
    }

    private val textWatcher = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.usernameTextEdit.error = null
            binding.emailTextEdit.error = null
            binding.passwordTextEdit.error = null
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }

    private fun setearTextWatcher() {
        binding.usernameTextEdit.addTextChangedListener(textWatcher)
        binding.emailTextEdit.addTextChangedListener(textWatcher)
        binding.passwordTextEdit.addTextChangedListener(textWatcher)
    }

    private fun configObservers() {
        viewModel.errorMessageIsEnabled.observe(viewLifecycleOwner, Observer{ error ->
            if (error){
                showDialog("Ha ocurrido un error obteniendo la informacion")
                binding.usernameTextEdit.error = "*campo obligatorio"
                binding.emailTextEdit.error = "*campo obligatorio"
                binding.passwordTextEdit.error = "*campo obligatorio"
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
            FirebaseLog.logSignUpClick()
            viewModel.saveNewRegister(binding.usernameTextEdit.text.toString(),
                binding.emailTextEdit.text.toString(),
                binding.passwordTextEdit.text.toString())
        }
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