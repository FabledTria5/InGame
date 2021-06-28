package com.example.ingame.data.network.model.game_developers

import com.google.gson.annotations.SerializedName

data class GameDevelopers(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<DevelopersResult>
)