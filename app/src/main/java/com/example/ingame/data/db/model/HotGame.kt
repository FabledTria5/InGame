package com.example.ingame.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hot_games")
data class HotGame(
    @PrimaryKey
    val gameId: Int,
    val gameImage: String,
    val gameTitle: String,
    val gameRating: String,
    val gameReleaseYear: String,
    val gamePlatforms: String
)