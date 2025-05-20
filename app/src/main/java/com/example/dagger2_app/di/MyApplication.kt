package com.example.dagger2_app.di

import android.app.Application


class MyApplication:Application() {

 val appComponent = DaggerAppComponent.create()

}