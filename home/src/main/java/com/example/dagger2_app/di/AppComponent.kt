package com.example.dagger2_app.di

import com.example.dagger2_app.HomeActivity
import com.example.dagger2_app.ui.fragments.add.AddNoteFragment
import com.example.dagger2_app.ui.fragments.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: HomeActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(addNoteFragment: AddNoteFragment)

}