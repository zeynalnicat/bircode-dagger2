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
import com.example.core.di.DaggerCoreComponent
import com.example.core.di.MyApplication
import com.example.dagger2_app.data.local.Injection
import com.example.dagger2_app.di.AppComponent
import com.example.dagger2_app.di.DaggerAppComponent
import com.example.dagger2_app.di.HomeAppModule
import com.example.dagger2_app.di.HomeViewModelModule
import com.example.home.R
import com.example.home.databinding.ActivityMiddleBinding
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MiddleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMiddleBinding

    private lateinit var resultLauncher : ActivityResultLauncher<Intent>

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private val appNavigator = AppNavigator(this,R.id.main)

    private lateinit var appComponent: AppComponent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMiddleBinding.inflate(layoutInflater)

        val coreComponent = (application as MyApplication).appComponent
        appComponent = DaggerAppComponent.builder().coreComponent(coreComponent).homeAppModule(
            HomeAppModule(this)).homeViewModelModule(HomeViewModelModule()).build()
        appComponent.inject(this)
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

             router.navigateTo(HomeNavigator.HomeActivityScreen())
        }

        binding.imgBack.setOnClickListener {
              onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(appNavigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }


}