package com.example.dagger2_app

import android.app.Activity
import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dagger2_app.data.local.Injection
import com.example.home.R
import com.example.home.databinding.ActivityMiddleBinding
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.androidx.AppNavigator

class MiddleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMiddleBinding

    private lateinit var resultLauncher : ActivityResultLauncher<Intent>
    private val appNavigator = AppNavigator(this,R.id.main)

    private lateinit var homeNavigator: HomeNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMiddleBinding.inflate(layoutInflater)
        homeNavigator = application as HomeNavigator
        homeNavigator.injectNavigator(appNavigator)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
             if(it.resultCode == RESULT_OK){
                 val userId = it.data?.getIntExtra("userId",-1)
                 binding.txtUserId.text = "UserId: ${userId}"
             }
        }


        binding.btnNext.setOnClickListener {
//
            homeNavigator.navigateForward()
//            val intent = Intent(this , HomeActivity::class.java)
//
//
//            resultLauncher.launch(intent)
        }

        binding.imgBack.setOnClickListener {
//           onBackPressedDispatcher.onBackPressed()
            homeNavigator.navigateBackToActivity()
        }
    }


}