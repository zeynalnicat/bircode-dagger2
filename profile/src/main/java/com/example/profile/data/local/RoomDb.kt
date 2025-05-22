package com.example.profile.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.profile.models.ProfileEntity


@Database(entities = [ProfileEntity::class], version = 1)
abstract class RoomDb: RoomDatabase() {


    abstract fun profileDao(): ProfileDao


}