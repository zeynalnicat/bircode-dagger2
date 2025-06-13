package com.example.dagger2_app.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.constants.AppQueries
import com.example.dagger2_app.models.NoteEntity


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteEntity: NoteEntity): Long

    @Query(AppQueries.NOTES_FETCH)
    suspend fun getNotes(): List<NoteEntity>

    @Delete
    suspend fun remove(note: NoteEntity)
}