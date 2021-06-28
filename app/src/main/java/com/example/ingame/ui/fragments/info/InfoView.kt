package com.example.ingame.ui.fragments.info

import com.example.ingame.data.network.model.game_developers.GameDevelopers
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface InfoView : MvpView {

    @AddToEndSingle
    fun setInfo(gameDevelopers: GameDevelopers)

}