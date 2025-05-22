package com.example.profile.di

import com.example.profile.data.local.SharedPreferenceHelper
import com.example.profile.ui.ProfileViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun provideProfileViewModel(sharedPreferenceHelper: SharedPreferenceHelper): ProfileViewModel{
        return ProfileViewModel(sharedPreferenceHelper)
    }


}