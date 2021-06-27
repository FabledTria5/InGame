package com.example.ingame.ui.fragments.about

import com.example.ingame.data.network.model.game_detail.GameDetails
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface AboutPresenterFactory {
    fun create(@Assisted gameDetails: GameDetails) : AboutPresenter
}