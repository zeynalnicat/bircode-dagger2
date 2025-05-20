package com.example.dagger2_app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dagger2_app.models.NoteEntity


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteEntity: NoteEntity):NoteEntity

    @Query("Select * from notes")
    suspend fun getNotes():List<NoteEntity>
}