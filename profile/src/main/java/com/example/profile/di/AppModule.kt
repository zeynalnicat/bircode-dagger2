package com.example.profile.di

import android.content.Context
import androidx.room.Room

import com.example.profile.data.RoomDb
import com.example.profile.data.local.ProfileDao
import com.example.profile.data.local.SharedPreferenceHelper
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ProfileAppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context{
        return context.applicationContext
    }

    @Singleton
    @Provides
    fun provideRoomDb(applicationContext: Context): RoomDb {
        return Room.databaseBuilder(
            applicationContext,
            RoomDb::class.java,
            "Users Table"

        ).build()
    }

    @Singleton
    @Provides
    fun provideProfileDao(roomDb: RoomDb): ProfileDao{
        return roomDb.profileDao()
    }

    @Singleton
    @Provides
    fun provideSharedPref(context: Context): SharedPreferenceHelper{
        return SharedPreferenceHelper(context)
    }




}