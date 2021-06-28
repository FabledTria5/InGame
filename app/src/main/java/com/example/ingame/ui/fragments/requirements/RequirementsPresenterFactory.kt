package com.example.ingame.ui.fragments.requirements

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface RequirementsPresenterFactory {
    fun create(@Assisted requirements: Pair<String, String>): RequirementsPresenter
}