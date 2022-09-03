package com.melvin.ongandroid.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.melvin.ongandroid.R
import com.melvin.ongandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //referencio al navHost
        val hostFragment = supportFragmentManager
            .findFragmentById(R.id.NavHostFragment) as NavHostFragment
        //referencio el navControler
        navController = hostFragment.navController

        //configuro los destinos en un mismo nivel
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.nosotrosFragment,
                R.id.novedadFragment,
                R.id.actividadFragment,
                R.id.contactoFragment
            )
        )
        //configuro el navControler con el actionBar
        setupActionBarWithNavController(navController, appBarConfiguration)
        //configuro el bottomNavigation con el navControler
        binding.bottomNavigation.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController
            .navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
