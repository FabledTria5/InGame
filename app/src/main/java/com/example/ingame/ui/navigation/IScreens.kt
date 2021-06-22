package com.example.ingame.ui.navigation

import com.example.ingame.data.network.model.games_list.Result
import com.example.ingame.ui.fragments.home.OnGameClickListener
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun home(): Screen
    fun catalogue(): Screen
    fun collections(): Screen
    fun profile(): Screen
    fun hotGames(gameInfo: Result, onGameClickListener: OnGameClickListener): Screen
    fun games(gameId: Int): Screen
}