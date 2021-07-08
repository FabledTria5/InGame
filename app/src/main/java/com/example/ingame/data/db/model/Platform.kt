package com.example.ingame.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "platforms")
data class Platform(
    @PrimaryKey
    val platformId: Int,
    val platformName: String
)