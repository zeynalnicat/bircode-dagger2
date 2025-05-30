package com.example.core.di

import android.app.Application


class MyApplication: Application() {

    lateinit var appComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerCoreComponent.builder().navigationModule(NavigationModule()).build()


    }




}