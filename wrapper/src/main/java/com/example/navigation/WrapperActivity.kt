package com.example.navigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.Screens

import com.example.di.AppComponent
import com.example.di.InjectionImpl
import com.example.di.MyApplication
import com.example.navigation.databinding.ActivityWrapperBinding
import com.github.terrakok.cicerone.androidx.AppNavigator
import java.net.URI
import androidx.core.net.toUri
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class WrapperActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWrapperBinding

    private lateinit var appComponent: AppComponent

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent = (application as MyApplication).appComponent
        appComponent.inject(this)
        enableEdgeToEdge()
        binding = ActivityWrapperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNote.setOnClickListener {
              val intent = Intent(Intent.ACTION_VIEW, "app://middle/page".toUri())
              startActivity(intent)
        }

        binding.btnProfile.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, "app://profile/page".toUri())
            startActivity(intent)
        }
    }


}