package com.example.ingame.ui.fragments.new

import dagger.assisted.AssistedFactory

@AssistedFactory
interface NewPresenterFactory {
    fun create(): NewPresenter
}