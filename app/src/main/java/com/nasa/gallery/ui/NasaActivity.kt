package com.nasa.gallery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.nasa.gallery.R
import com.nasa.gallery.databinding.ActivityNasaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NasaActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        findNavController(R.id.main_nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNasaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}
