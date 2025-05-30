package com.example.dagger2_app.di

import com.example.core.di.CoreComponent
import com.example.dagger2_app.HomeActivity
import com.example.dagger2_app.MiddleActivity
import com.example.dagger2_app.ui.fragments.add.AddNoteFragment
import com.example.dagger2_app.ui.fragments.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [HomeAppModule::class, HomeViewModelModule::class], dependencies = [CoreComponent::class])
interface AppComponent {

    fun inject(homeActivity: HomeActivity)
    fun inject(middleActivity: MiddleActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(addNoteFragment: AddNoteFragment)
}