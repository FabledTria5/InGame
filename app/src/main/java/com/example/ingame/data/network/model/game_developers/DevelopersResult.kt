package com.example.ingame.data.network.model.game_developers

import com.google.gson.annotations.SerializedName

data class DevelopersResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("positions")
    val positions: List<Position>,
)