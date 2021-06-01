package com.example.ingame.ui.navigation

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun home(): Screen
    fun catalogue(): Screen
    fun collections(): Screen
    fun profile(): Screen
}