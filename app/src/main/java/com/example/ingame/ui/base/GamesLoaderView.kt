package com.example.ingame.ui.base

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface GamesLoaderView : MvpView {

    @AddToEndSingle
    fun setupRecycler()

    @Skip
    fun fillList()

    @Skip
    fun clearList()
}