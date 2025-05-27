package com.example.di

import android.app.Application
import com.example.Screens
import com.example.dagger2_app.HomeActivity
import com.example.dagger2_app.HomeNavigator
import com.example.dagger2_app.MiddleActivity
import com.example.dagger2_app.data.local.Injection
import com.example.dagger2_app.di.HomeAppModule
import com.example.dagger2_app.di.HomeViewModelModule
import com.example.dagger2_app.ui.fragments.add.AddNoteFragment
import com.example.dagger2_app.ui.fragments.home.HomeFragment
import com.example.profile.ProfileActivity
import com.example.profile.ProfileNavigator
import com.example.profile.data.ProfileInjection
import com.example.profile.di.ProfileAppModule
import com.example.profile.di.ProfileViewModelModule
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.AppNavigator


class MyApplication: Application(), Injection, ProfileInjection, HomeNavigator, ProfileNavigator {

    companion object {
        lateinit var cicerone: Cicerone<Router>
        lateinit var router: Router
        lateinit var navigatorHolder:NavigatorHolder
    }


    lateinit var appComponent: AppComponent
    private lateinit var homeInjection: Injection
    private lateinit var profileInjection: ProfileInjection

    override fun onCreate() {
        super.onCreate()
        cicerone = Cicerone.create()
        router = cicerone.router
        navigatorHolder = cicerone.getNavigatorHolder()

        appComponent = DaggerAppComponent.builder().profileAppModule(ProfileAppModule(this)).profileViewModelModule(
            ProfileViewModelModule()).homeAppModule(HomeAppModule(this)).homeViewModelModule(
            HomeViewModelModule()).build()

        homeInjection = InjectionImpl(appComponent)
        profileInjection = InjectionImpl(appComponent)
    }

    override fun inject(homeActivity: HomeActivity) {
        homeInjection.inject(homeActivity)
    }

    override fun inject(homeFragment: HomeFragment) {
        homeInjection.inject(homeFragment)
    }

    override fun inject(addNoteFragment: AddNoteFragment) {
        homeInjection.inject(addNoteFragment)
    }


    override fun inject(profileActivity: ProfileActivity) {
        profileInjection.inject(profileActivity)
    }

    override fun navigateForward() {
        router.navigateTo(Screens.HomeActivityScreen())
    }


    override fun injectNavigator(appNavigator: AppNavigator) {
         navigatorHolder.setNavigator(appNavigator)
    }

    override fun navigateToAddNotesFragment() {
         router.navigateTo(Screens.AddNotesFragmentScreen())
    }

    override fun navigateToHomeFragment() {
         router.navigateTo(Screens.NotesFragmentScreen())
    }


    override fun navigateBackToActivity() {
        router.finishChain()
    }

    override fun getRouter(): Router {
        return router
    }

    override fun navigateBackToHomeFragment() {
        router.backTo(Screens.NotesFragmentScreen())
    }



}