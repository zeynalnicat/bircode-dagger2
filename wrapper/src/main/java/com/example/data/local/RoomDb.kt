package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dagger2_app.data.local.NoteDao
import com.example.dagger2_app.models.NoteEntity
import com.example.profile.data.local.ProfileDao
import com.example.profile.models.ProfileEntity


@Database(entities = [ProfileEntity::class, NoteEntity::class], version = 2)
abstract class RoomDb: RoomDatabase() {


    abstract fun profileDao(): ProfileDao
    abstract fun noteDao(): NoteDao


}