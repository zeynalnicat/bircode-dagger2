package com.example.dagger2_app

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dagger2_app.data.local.Injection
import com.example.dagger2_app.ui.fragments.home.HomeFragment
import com.example.home.R
import com.example.home.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.androidx.AppNavigator


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val appNavigator = object : AppNavigator(this,R.id.fragmentContainerView){
        override fun back() {
            super.back()
        }
    }


    private lateinit var homeNavigator: HomeNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as Injection).inject(this)
        homeNavigator = application as HomeNavigator
        homeNavigator.injectNavigator(appNavigator)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        if(savedInstanceState==null){
            homeNavigator.navigateToHomeFragment()
        }







    }







}