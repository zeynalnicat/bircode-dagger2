package com.example.core.di

import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Component
import javax.inject.Singleton


@Component(modules = [NavigationModule::class])
interface CoreComponent {
    fun navigatorHolder(): NavigatorHolder
    fun router(): Router
}