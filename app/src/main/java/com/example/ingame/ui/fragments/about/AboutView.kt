package com.example.ingame.ui.fragments.about

import com.example.ingame.data.network.model.screenshots.ScreenshotsResult
import com.example.ingame.ui.base.ErrorView
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface AboutView : ErrorView {

    @AddToEndSingle
    fun setSnapshots(snapshots: List<ScreenshotsResult>)

}