package com.example.ingame.data.network.model.game_detail

import java.io.Serializable

data class Publisher(
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
) : Serializable