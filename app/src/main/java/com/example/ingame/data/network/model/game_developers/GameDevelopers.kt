package com.example.ingame.data.network.model.game_developers

data class GameDevelopers(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<DevelopersResult>
)