package com.example.ingame.ui.fragments.catalogue

import dagger.assisted.AssistedFactory

@AssistedFactory
interface CataloguePresenterFactory {
    fun create(): CataloguePresenter
}