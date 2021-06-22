package com.example.ingame.data.network.model.common

import com.example.ingame.data.network.model.game_detail.Requirements

data class Platforms(
    val platform: Platform,
    val released_at: String,
    val requirements: Requirements
)