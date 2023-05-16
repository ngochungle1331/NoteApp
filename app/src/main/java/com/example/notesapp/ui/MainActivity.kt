package com.example.notesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        navController = findNavController(R.id.fragmentContainerView)
        setupActionBarWithNavController(navController)


    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}