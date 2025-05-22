package com.example.profile.di

import android.app.Application



class ProfileApplication: Application() {

    lateinit var appComponent: ProfileAppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerProfileAppComponent.create()
    }
}