package com.example.ingame.data.network.model.common

import com.example.ingame.data.network.model.game_detail.Requirements
import java.io.Serializable

data class Platforms(
    val platform: Platform,
    val released_at: String,
    val requirements: Requirements
) : Serializable