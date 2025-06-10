package com.example.dagger2_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.core.di.MyApplication
import com.example.dagger2_app.di.AppComponent
import com.example.dagger2_app.di.DaggerAppComponent
import com.example.dagger2_app.di.HomeAppModule
import com.example.dagger2_app.di.HomeViewModelModule
import com.example.home.R
import com.example.home.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    lateinit var appComponent: AppComponent
    private val appNavigator = object : AppNavigator(this, R.id.fragmentContainerView) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val coreComponent = (application as MyApplication).appComponent
        appComponent = DaggerAppComponent.builder().coreComponent(coreComponent).homeAppModule(
            HomeAppModule(this)
        ).homeViewModelModule(HomeViewModelModule()).build()
        appComponent.inject(this)

        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        if (savedInstanceState == null) {
            router.navigateTo(HomeNavigator.NotesFragmentScreen())
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