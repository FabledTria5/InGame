package com.example.ingame.data.network.model.games_list

data class GamesList(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Result>
)