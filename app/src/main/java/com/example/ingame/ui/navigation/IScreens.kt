package com.example.ingame.ui.navigation

import com.example.ingame.data.network.model.games_list.Result
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun home(): Screen
    fun catalogue(): Screen
    fun collections(): Screen
    fun profile(): Screen
    fun hotGames(gameInfo: Result): Screen
    fun games(gameId: Int): Screen
}