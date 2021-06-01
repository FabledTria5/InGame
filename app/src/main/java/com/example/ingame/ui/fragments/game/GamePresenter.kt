package com.example.ingame.ui.fragments.game

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class GamePresenter(private val router: Router) : MvpPresenter<GameView>() {

    fun backPressed() : Boolean {
        router.exit()
        return true
    }
}