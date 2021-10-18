package com.example.ingame.ui.activities.main

import dagger.assisted.AssistedFactory

@AssistedFactory
interface MainPresenterFactory {
    fun create(): MainPresenter
}