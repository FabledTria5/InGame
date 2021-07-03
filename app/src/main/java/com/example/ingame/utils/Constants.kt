package com.example.ingame.utils

object Constants {
    const val HOT_GAMES_TICK_RATE = 6000L
    const val HOT_GAMES_DELAY = (HOT_GAMES_TICK_RATE + 1000).toInt()
    const val GAMES_BASE_URL = "https://api.rawg.io/"

    const val APP_PREFERENCES = "app_preferences"
    const val PREFERENCE_DATE = "date"
}