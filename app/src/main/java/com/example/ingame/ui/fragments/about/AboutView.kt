package com.example.ingame.ui.fragments.about

import com.example.ingame.data.network.model.screenshots.ScreenshotsResult
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface AboutView : MvpView {

    @AddToEndSingle
    fun setSnapshots(snapshots: List<ScreenshotsResult>)

}