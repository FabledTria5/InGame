package com.example.ingame.data.network.model.game_developers

import com.google.gson.annotations.SerializedName

data class Position(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
)