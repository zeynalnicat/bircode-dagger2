package com.example.profile.di

import com.example.profile.ProfileActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ProfileAppModule::class, ViewModelModule::class])
interface ProfileAppComponent {

    fun inject(activity: ProfileActivity)
}