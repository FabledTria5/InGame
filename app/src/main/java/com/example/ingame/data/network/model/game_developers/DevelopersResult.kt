package com.example.ingame.data.network.model.game_developers

data class DevelopersResult(
    val games_count: Int,
    val id: Int,
    val image: String,
    val image_background: String,
    val name: String,
    val positions: List<Position>,
    val slug: String
)