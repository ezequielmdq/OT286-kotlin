package com.melvin.ongandroid.view.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.melvin.ongandroid.R
import com.melvin.ongandroid.application.ONGApplication.Companion.prefs
import com.melvin.ongandroid.databinding.FragmentLoginBinding
import com.melvin.ongandroid.view.LoginActivity
import com.melvin.ongandroid.model.LogIn
import com.melvin.ongandroid.model.repository.Network.implement.LogInDataRepository

import com.melvin.ongandroid.view.MainActivity
import com.melvin.ongandroid.viewmodel.LogInViewModel
import com.melvin.ongandroid.viewmodel.LogInViewModelFactory
import java.util.*


class LoginFragment : Fragment() {

     lateinit var binding: FragmentLoginBinding
     private val callbackManager = CallbackManager.Factory.create()

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
        binding.tvOlvidoContrasenia.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }


        binding.btLogin.setOnClickListener {
            viewModel.logIn(LogIn(binding.tiEmail.text.toString(), binding.tiContrasenia.text.toString()))
        }

        /** estos metodos maneja el resultado del icinio de sesion. Si es satisfactorio se ejecutara lo
         * que esta en onSucces, sy se cancela lo que esta en on Cancel y si hay algun error
         * lo que esta en on error
         */

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onCancel() {
                    LoginManager.getInstance().logOut()

                }

                override fun onError(error: FacebookException) {
                    LoginManager.getInstance().logOut()
                }

                override fun onSuccess(result: LoginResult) {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }
            })

        /** listener boton de facebook para inicio de sesion*/

        binding.btnFacebook?.setOnClickListener{
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
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

    private fun startLogin(){
      viewModel.loginError.observe(viewLifecycleOwner){
          when(it){


          }
      }


        }

    }


    /** el método onActivityResult, llama a callbackManager.onActivityResult para pasar
    los resultados del inicio de sesión al objeto LoginManager mediante callbackManager.*/

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }


}