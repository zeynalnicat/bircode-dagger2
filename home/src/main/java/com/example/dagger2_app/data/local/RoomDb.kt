package com.example.dagger2_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dagger2_app.models.NoteEntity


@Database(entities = [NoteEntity::class], version = 3)
abstract class RoomDb : RoomDatabase() {

    abstract fun noteDao(): NoteDao


}