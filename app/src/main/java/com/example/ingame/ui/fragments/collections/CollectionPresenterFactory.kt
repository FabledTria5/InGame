package com.example.ingame.ui.fragments.collections

import dagger.assisted.AssistedFactory

@AssistedFactory
interface CollectionPresenterFactory {
    fun create(): CollectionPresenter
}