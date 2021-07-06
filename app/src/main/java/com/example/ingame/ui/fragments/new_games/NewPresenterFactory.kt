package com.example.ingame.ui.fragments.new_games

import dagger.assisted.AssistedFactory

@AssistedFactory
interface NewPresenterFactory {
    fun create(): NewGamesPresenter
}