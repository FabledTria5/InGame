package com.example.ingame.ui.base

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface TabView : MvpView {

    @AddToEndSingle
    fun selectPageText(page: Int)

    @AddToEndSingle
    fun unselectPageText(page: Int)
}