package com.example.ingame.ui.fragments.info

import com.example.ingame.data.network.model.game_developers.GameDevelopers
import com.example.ingame.ui.base.ErrorView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface InfoView : ErrorView {

    @AddToEndSingle
    fun setInfo(gameDevelopers: GameDevelopers)

}