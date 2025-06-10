package com.example.profile.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.profile.data.local.ProfileDao
import com.example.profile.models.ProfileEntity

@Database(entities = [ProfileEntity::class], version = 3)
abstract class RoomDb: RoomDatabase() {

    abstract fun profileDao(): ProfileDao


}