package com.example.di

import com.example.dagger2_app.data.local.NoteDao
import com.example.dagger2_app.ui.fragments.add.AddNoteViewModel
import com.example.dagger2_app.ui.fragments.home.HomeViewModel
import com.example.profile.data.local.SharedPreferenceHelper
import com.example.profile.ui.ProfileViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun provideProfileViewModel(sharedPreferenceHelper: SharedPreferenceHelper): ProfileViewModel {
        return ProfileViewModel(sharedPreferenceHelper)
    }

    @Provides
    @Singleton
    fun provideHomeViewModel(noteDao: NoteDao):HomeViewModel{
        return HomeViewModel(noteDao)
    }

    @Provides
    @Singleton
    fun provideAddNoteViewModel(noteDao: NoteDao): AddNoteViewModel{
        return AddNoteViewModel(noteDao)
    }

}