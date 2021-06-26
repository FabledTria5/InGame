package com.example.ingame.data.network.model.games_list

import java.io.Serializable

data class Tag(
    val id: Int,
    val name: String,
    val image_background: String
) : Serializable
