package com.example.ingame.ui.fragments.hot_game

import com.example.ingame.data.db.model.HotGame
import com.example.ingame.ui.base.ErrorView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface HotGameView : ErrorView {

    @AddToEndSingle
    fun setGameInfo(hotGame: HotGame)

}