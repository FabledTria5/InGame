package com.example.ingame.ui.fragments.search

import dagger.assisted.AssistedFactory

@AssistedFactory
interface SearchPresenterFactory {
    fun create(): SearchPresenter
}