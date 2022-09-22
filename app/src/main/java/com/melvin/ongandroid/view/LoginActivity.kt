package com.melvin.ongandroid.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.melvin.ongandroid.R
import com.melvin.ongandroid.businesslogic.FirebaseLog
import com.melvin.ongandroid.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {


    lateinit var binding: ActivityLoginBinding

    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var signInRequest: BeginSignInRequest

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001

    }



        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)

            //setTheme(R.style.Theme_AppCompat_Light_NoActionBar)
            supportActionBar?.hide()

            // [START config_signin]
            // Configure Google Sign In
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()


            googleSignInClient = GoogleSignIn.getClient(this, gso)
            // [END config_signin]

            // [START initialize_auth]
            // Initialize Firebase Auth
            auth = FirebaseAuth.getInstance()
            // [END initialize_auth]
        }

        // [START on_start_check_user]
        override fun onStart() {
            super.onStart()
            // Check if user is signed in (non-null) and update UI accordingly.
            val currentUser = auth.currentUser
            if (currentUser != null) {
                updateUI(currentUser)
            }
        }
        // [END on_start_check_user]

        // [START onactivityresult]
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
            if (requestCode == RC_SIGN_IN) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Toast.makeText(this, "conexion exitosa", Toast.LENGTH_LONG).show()
                    Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    FirebaseLog.logLogInError()
                    // Google Sign In failed, update UI appropriately
                    Toast.makeText(this, "conexion fallida", Toast.LENGTH_LONG).show()
                    Log.w(TAG, "Google sign in failed", e)
                }
            }
        }
        // [END onactivityresult]

        // [START auth_with_google]
        private fun firebaseAuthWithGoogle(idToken: String) {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        FirebaseLog.logLogInSuccess()
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        FirebaseLog.logLogInError()
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        updateUI(null)
                    }
                }
        }
        // [END auth_with_google]

        // [START signin]
        fun signIn() {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
        // [END signin]

        private fun updateUI(user: FirebaseUser?) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }



    }
