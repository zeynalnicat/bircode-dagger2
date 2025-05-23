package com.example.di

import android.content.Context
import androidx.room.Room
import com.example.dagger2_app.data.local.Injection
import com.example.dagger2_app.data.local.NoteDao
import com.example.dagger2_app.ui.adapters.NotesAdapter
import com.example.data.local.RoomDb
import com.example.profile.data.local.SharedPreferenceHelper
import com.example.profile.data.local.ProfileDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private val context: Context) {


    @Singleton
    @Provides
    fun provideApplicationContext(): Context{
        return context.applicationContext
    }

    @Singleton
    @Provides
    fun provideRoomDb(applicationContext: Context): RoomDb{
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

    @Singleton
    @Provides
    fun provideNoteDao(roomDb: RoomDb): NoteDao {
        return roomDb.noteDao()
    }

    @Singleton
    @Provides
    fun provideNotesAdapter(): NotesAdapter{
        return NotesAdapter()
    }



}