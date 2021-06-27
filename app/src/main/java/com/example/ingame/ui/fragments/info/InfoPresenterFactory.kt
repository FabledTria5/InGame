package com.example.ingame.ui.fragments.info

import com.example.ingame.data.network.model.game_detail.GameDetails
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface InfoPresenterFactory {
    fun create(@Assisted gameDetails: GameDetails): InfoPresenter
}