package com.example.ingame.ui.base

import moxy.MvpView
import moxy.viewstate.strategy.alias.Skip

interface ErrorView : MvpView {
    @Skip
    fun showError()
}