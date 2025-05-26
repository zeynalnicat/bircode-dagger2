package com.example.di

import android.app.Application
import com.example.dagger2_app.HomeActivity
import com.example.dagger2_app.MiddleActivity
import com.example.dagger2_app.data.local.Injection
import com.example.dagger2_app.ui.fragments.add.AddNoteFragment
import com.example.dagger2_app.ui.fragments.home.HomeFragment
import com.example.profile.ProfileActivity
import com.example.profile.data.ProfileInjection

class InjectionImpl(private val appComponent: AppComponent): Injection, ProfileInjection {
    override fun inject(homeActivity: HomeActivity) {
        appComponent.inject(homeActivity)
    }

    override fun inject(homeFragment: HomeFragment) {
        appComponent.inject(homeFragment)
    }

    override fun inject(addNoteFragment: AddNoteFragment) {
        appComponent.inject(addNoteFragment)
    }


    override fun inject(profileActivity: ProfileActivity) {
        appComponent.inject(profileActivity)
    }
}