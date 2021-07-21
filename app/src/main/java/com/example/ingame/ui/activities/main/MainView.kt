package com.example.ingame.ui.activities.main

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView : MvpView {

    @AddToEndSingle
    fun setBottomNavTextGradient(position: Int)

    @AddToEndSingle
    fun clearBottomNavText(position: Int)

}