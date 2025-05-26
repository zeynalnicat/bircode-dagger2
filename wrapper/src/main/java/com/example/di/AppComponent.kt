package com.example.di

import com.example.dagger2_app.HomeActivity
import com.example.dagger2_app.di.HomeAppModule
import com.example.dagger2_app.di.HomeViewModelModule
import com.example.dagger2_app.ui.fragments.add.AddNoteFragment
import com.example.dagger2_app.ui.fragments.home.HomeFragment
import com.example.profile.ProfileActivity
import com.example.navigation.WrapperActivity
import com.example.profile.di.ProfileAppModule
import com.example.profile.di.ProfileViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [HomeAppModule::class, HomeViewModelModule::class, ProfileAppModule::class, ProfileViewModelModule::class])
interface AppComponent {

    fun inject(activity: ProfileActivity)
    fun inject(mainActivity: HomeActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(addNoteFragment: AddNoteFragment)
    fun inject(wrapperActivity: WrapperActivity)
}