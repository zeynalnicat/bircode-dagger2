package com.example.profile

import com.github.terrakok.cicerone.androidx.AppNavigator

interface ProfileNavigator {

    fun navigateBackToActivity()

    fun injectNavigator(appNavigator: AppNavigator)
}