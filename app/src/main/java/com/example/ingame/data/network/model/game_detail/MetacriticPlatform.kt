package com.example.ingame.data.network.model.game_detail

import java.io.Serializable

data class MetacriticPlatform(
    val metascore: Int,
    val url: String
) : Serializable