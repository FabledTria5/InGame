package com.example.ingame.ui.fragments.game

import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.ui.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle

interface GameView : BaseView {

    @AddToEndSingle
    fun setGameData(gameDetails: GameDetails)
}