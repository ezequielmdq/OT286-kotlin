package com.melvin.ongandroid.view.login

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.melvin.ongandroid.R
import com.melvin.ongandroid.application.ONGApplication.Companion.prefs
import com.melvin.ongandroid.application.Validator
import com.melvin.ongandroid.businesslogic.FirebaseLog
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



        binding.btnGoogle?.setOnClickListener {
            FirebaseLog.logGmailPressed()
            val loginActivity = requireActivity() as LoginActivity
            loginActivity.signIn()
        }
        binding.tvOlvidoContrasenia?.setOnClickListener{
            FirebaseLog.logSignUpPressed()
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }


        binding.loginBtn?.setOnClickListener {

            viewModel.logIn(LogIn(binding.etEmailLogin?.text.toString(), binding.etPasswordLogin?.text.toString()))
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
                    FirebaseLog.logLogInSuccess()
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }
            })

        /** listener boton de facebook para inicio de sesion*/

        binding.btnFacebook?.setOnClickListener{
            FirebaseLog.logFacebookPressed()
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        }

        validarCampos()
        configObservables()
        return binding.root
    }

    private fun validarCampos() {
        binding.etEmailLogin?.addTextChangedListener(textWatcher)
        binding.etPasswordLogin?.addTextChangedListener(textWatcher)
    }

    //Configura los observables del viewmodel
    private fun configObservables() {
        viewModel.token.observe(viewLifecycleOwner, Observer {
            FirebaseLog.logLogInSuccess()

            prefs.saveToken(viewModel.token.value.toString())


            if(viewModel.token.value != null){
                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            FirebaseLog.logLogInError()
            showDialog()
        })
    }

    private fun configObservers() {
        /*viewModel.token.observe(this, Observer {
            when(it){
                binding.etEmailLogin?.error -> showDialog("","")
                binding.etPasswordLogin?.error -> showDialog("","")
                else -> "Success"

            }
        })*/
    }

    private fun showDialog() {

            context?.let {
                AlertDialog.Builder(it).setMessage(getString(R.string.error_login)).setTitle("Error")
                    .setNeutralButton(
                        "Aceptar"
                    ) { _, _ -> }
                    .create()
                    .show()
            }



    }

    /**
     * Funcion que verifica si el email y la contraseña cumplen con las ocndiciones
     */
    private val textWatcher : TextWatcher = object  : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val email = binding.etEmailLogin?.text.toString().trim()
            val password = binding.etPasswordLogin?.text.toString().trim()

            //Sin la validacion de contraseña por ahora mientras la api siga funcionando mal para registrarse
            if(email.isNotEmpty() && password.isNotEmpty() && Validator.isEmailValid(email) && Validator.isPasswordValid(password)){
                binding.loginBtn?.setBackgroundColor(Color.RED)
                binding.loginBtn?.setTextColor(Color.WHITE)
                binding.loginBtn?.isEnabled =  true
            }else{
                binding.loginBtn?.setBackgroundColor(resources.getColor(R.color.botom_disable))
                binding.loginBtn?.setTextColor(Color.BLACK)
                binding.loginBtn?.isEnabled =  false
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }



    /** el método onActivityResult, llama a callbackManager.onActivityResult para pasar
    los resultados del inicio de sesión al objeto LoginManager mediante callbackManager.*/

    @Override
   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)


        //show error dialog if there's an issue with the login api
   }

}