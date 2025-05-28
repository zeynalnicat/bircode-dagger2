package com.example.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Cicerone.Companion.create
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

import javax.inject.Singleton


@Module
class NavigationModule {
    private val cicerone: Cicerone<Router> = create()

    @Singleton
    @Provides
    fun providerRouter(): Router{
         return cicerone.router
    }

    @Provides
    @Singleton
    fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }
}