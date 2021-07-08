package com.example.ingame.ui.fragments.home

import dagger.assisted.AssistedFactory

@AssistedFactory
interface HomePresenterFactory {

    fun create(): HomePresenter

}