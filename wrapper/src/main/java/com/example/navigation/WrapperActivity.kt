package com.example.navigation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dagger2_app.HomeActivity
import com.example.dagger2_app.data.local.Injection
import com.example.di.AppComponent
import com.example.di.InjectionImpl
import com.example.di.MyApplication
import com.example.navigation.databinding.ActivityWrapperBinding
import com.example.profile.ProfileActivity

class WrapperActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWrapperBinding

    private lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent = (application as MyApplication).appComponent
        appComponent.inject(this)
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