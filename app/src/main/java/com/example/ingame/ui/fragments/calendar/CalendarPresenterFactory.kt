package com.example.ingame.ui.fragments.calendar

import dagger.assisted.AssistedFactory

@AssistedFactory
interface CalendarPresenterFactory {
    fun create(): CalendarPresenter
}