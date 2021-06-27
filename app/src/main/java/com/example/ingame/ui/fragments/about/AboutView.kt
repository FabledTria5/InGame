package com.example.ingame.ui.fragments.about

import com.example.ingame.data.network.model.screenshots.Result
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface AboutView : MvpView {

    @AddToEndSingle
    fun setSnapshots(snapshots: List<Result>)

}