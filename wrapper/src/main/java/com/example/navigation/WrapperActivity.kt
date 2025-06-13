package com.example.navigation

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.navigation.databinding.ActivityWrapperBinding

import androidx.core.net.toUri
import com.example.core.constants.AppLinks
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import java.util.jar.Manifest
import javax.inject.Inject

class WrapperActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWrapperBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityWrapperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {

            }.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }

        binding.btnNote.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, AppLinks.MIDDLE_PAGE.toUri())
            startActivity(intent)
        }

        binding.btnProfile.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, AppLinks.PROFILE_PAGE.toUri())
            startActivity(intent)
        }
    }


}