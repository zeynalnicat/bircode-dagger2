package com.example.dagger2_app.di

import com.example.dagger2_app.data.local.NoteDao
import com.example.dagger2_app.ui.fragments.home.HomeViewModel
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HomeViewModelModule {


    @Provides
    @Singleton
    fun provideHomeViewModel(noteDao: NoteDao, router: Router): HomeViewModel {
        return HomeViewModel(noteDao, router)
    }


}