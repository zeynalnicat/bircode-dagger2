package com.example.navigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.navigation.databinding.ActivityWrapperBinding

import androidx.core.net.toUri
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class WrapperActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWrapperBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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