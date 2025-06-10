package com.example.profile.di

import com.example.core.di.CoreComponent
import com.example.profile.ProfileActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [ProfileAppModule::class, ProfileViewModelModule::class],
    dependencies = [CoreComponent::class]
)
interface AppComponent {

    fun inject(profileActivity: ProfileActivity)
}