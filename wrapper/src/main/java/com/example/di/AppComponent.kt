package com.example.di

import com.example.dagger2_app.HomeActivity
import com.example.dagger2_app.ui.fragments.add.AddNoteFragment
import com.example.dagger2_app.ui.fragments.home.HomeFragment
import com.example.profile.ProfileActivity
import com.example.di.ViewModelModule
import com.example.navigation.WrapperActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity: ProfileActivity)
    fun inject(mainActivity: HomeActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(addNoteFragment: AddNoteFragment)
    fun inject(wrapperActivity: WrapperActivity)
}