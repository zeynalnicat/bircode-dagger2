package com.example.dagger2_app

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.AppNavigator

interface HomeNavigator {


    fun navigateForward()

    fun injectNavigator(appNavigator: AppNavigator)

    fun navigateToAddNotesFragment()

    fun navigateToHomeFragment()

    fun navigateBackToHomeFragment()

    fun navigateBackToActivity()

    fun getRouter(): Router

}