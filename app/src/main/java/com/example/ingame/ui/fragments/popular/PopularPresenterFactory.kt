package com.example.ingame.ui.fragments.popular

import dagger.assisted.AssistedFactory

@AssistedFactory
interface PopularPresenterFactory {
    fun create(): PopularPresenter
}