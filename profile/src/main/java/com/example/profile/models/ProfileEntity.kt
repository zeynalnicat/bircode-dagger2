package com.example.profile.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String

)