package com.example.ingame.ui.fragments.profile

import dagger.assisted.AssistedFactory

@AssistedFactory
interface ProfilePresenterFactory {
    fun create(): ProfilePresenter
}