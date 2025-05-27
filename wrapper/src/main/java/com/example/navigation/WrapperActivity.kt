package com.example.navigation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.Screens

import com.example.di.AppComponent
import com.example.di.InjectionImpl
import com.example.di.MyApplication
import com.example.navigation.databinding.ActivityWrapperBinding
import com.github.terrakok.cicerone.androidx.AppNavigator

class WrapperActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWrapperBinding

    private val navigator = AppNavigator(this,R.id.main)

    private lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent = (application as MyApplication).appComponent
        appComponent.inject(this)
        enableEdgeToEdge()
        binding = ActivityWrapperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNote.setOnClickListener {
            MyApplication.router.navigateTo(Screens.MiddleActivityScreen())
        }

        binding.btnProfile.setOnClickListener {
            MyApplication.router.navigateTo(Screens.ProfileActivityScreen())
        }
    }
    override fun onResumeFragments() {
        super.onResumeFragments()
        MyApplication.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        MyApplication.navigatorHolder.removeNavigator()
    }

}