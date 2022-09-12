package com.melvin.ongandroid.view.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.melvin.ongandroid.R
import com.melvin.ongandroid.application.ONGApplication.Companion.prefs
import com.melvin.ongandroid.databinding.FragmentLoginBinding
import com.melvin.ongandroid.view.LoginActivity

import com.melvin.ongandroid.model.LogIn
import com.melvin.ongandroid.model.repository.Network.implement.LogInDataRepository
import com.melvin.ongandroid.viewmodel.LogInViewModel
import com.melvin.ongandroid.viewmodel.LogInViewModelFactory

class LoginFragment : Fragment() {

     lateinit var binding: FragmentLoginBinding

    private val viewModel : LogInViewModel by activityViewModels(
        factoryProducer = {
            LogInViewModelFactory(LogInDataRepository())
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btLogInGogle?.setOnClickListener {
            val loginActivity = requireActivity() as LoginActivity
            loginActivity.signIn()
        }

        binding.btLogin.setOnClickListener {
            viewModel.logIn(LogIn(binding.tiEmail.text.toString(), binding.tiContrasenia.text.toString()))
        }


        configObservables()
        return binding.root
    }

    //Configura los observables del viewmodel
    private fun configObservables() {
        viewModel.token.observe(viewLifecycleOwner, Observer {

            prefs.saveToken(viewModel.token.value.toString())

        })
    }


}