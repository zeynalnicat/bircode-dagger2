package com.example.di

import android.app.Application
import com.example.dagger2_app.HomeActivity
import com.example.dagger2_app.data.local.Injection
import com.example.dagger2_app.ui.fragments.add.AddNoteFragment
import com.example.dagger2_app.ui.fragments.home.HomeFragment
import com.example.profile.ProfileActivity
import com.example.profile.data.ProfileInjection


class MyApplication: Application(), Injection, ProfileInjection {

    lateinit var appComponent: AppComponent
    private lateinit var homeInjection: Injection
    private lateinit var profileInjection: ProfileInjection

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).viewModelModule(
            ViewModelModule()
        ).build()

        homeInjection = InjectionImpl(appComponent)
        profileInjection = InjectionImpl(appComponent)
    }

    override fun inject(homeActivity: HomeActivity) {
        homeInjection.inject(homeActivity)
    }

    override fun inject(homeFragment: HomeFragment) {
        homeInjection.inject(homeFragment)
    }

    override fun inject(addNoteFragment: AddNoteFragment) {
        homeInjection.inject(addNoteFragment)
    }

    override fun inject(profileActivity: ProfileActivity) {
        profileInjection.inject(profileActivity)
    }
}