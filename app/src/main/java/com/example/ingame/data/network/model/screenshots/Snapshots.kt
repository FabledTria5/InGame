package com.example.ingame.data.network.model.screenshots

data class Snapshots(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<ScreenshotsResult>
)