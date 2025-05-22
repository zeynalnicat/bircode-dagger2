package com.example.dagger2_app.di

import android.app.Application


class MyApplication:Application() {
  lateinit var daggerAppComponent: AppComponent

// val appComponent: AppComponent by lazy {
//  DaggerAppComponent.factory().create(applicationContext)
// }

 override fun onCreate() {
  super.onCreate()
  daggerAppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).viewModelModule(
   ViewModelModule()).build()
 }

}