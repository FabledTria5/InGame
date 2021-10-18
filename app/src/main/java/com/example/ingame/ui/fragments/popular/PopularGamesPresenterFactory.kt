package com.example.ingame.ui.fragments.popular

import dagger.assisted.AssistedFactory

@AssistedFactory
interface PopularGamesPresenterFactory {
    fun create(): PopularGamesPresenter
}