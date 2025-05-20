package com.example.dagger2_app.di

import com.example.dagger2_app.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)


}