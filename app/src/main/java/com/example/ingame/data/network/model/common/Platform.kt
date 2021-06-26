package com.example.ingame.data.network.model.common

import java.io.Serializable

data class Platform(
    val id: Int,
    val name: String,
    val slug: String
) : Serializable