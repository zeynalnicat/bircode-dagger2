package com.example.navigation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dagger2_app.HomeActivity
import com.example.navigation.databinding.ActivityWrapperBinding
import com.example.profile.ProfileActivity

class WrapperActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWrapperBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWrapperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNote.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}