package com.example.dagger2_app.di

import android.content.Context
import androidx.room.Room
import com.example.dagger2_app.data.local.NoteDao
import com.example.dagger2_app.data.local.RoomDb
import com.example.dagger2_app.ui.adapters.NotesAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class HomeAppModule(private val context: Context) {


    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }

    @Provides
    fun provideRoomDb(applicationContext: Context): RoomDb {
        return Room.databaseBuilder(
            applicationContext,
            RoomDb::class.java,
            "Notes Table"
        ).build()
    }


    @Singleton
    @Provides
    fun provideNoteDao(roomDb: RoomDb): NoteDao {
        return roomDb.noteDao()
    }

    @Singleton
    @Provides
    fun provideNotesAdapter(): NotesAdapter {
        return NotesAdapter()
    }


}