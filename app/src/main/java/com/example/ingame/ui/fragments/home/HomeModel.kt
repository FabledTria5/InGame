package com.example.ingame.ui.fragments.home

class HomeModel(private val sliderItemsCount: Int) {

    private var currentSliderItem = 0

    fun nextPage() = currentSliderItem + 1

    fun resetSliderItem() {
        currentSliderItem = 0
    }

    fun isSliderFinished() = currentSliderItem == sliderItemsCount - 1

    fun setCurrentSliderItem(sliderItem: Int) {
        currentSliderItem = sliderItem
    }

    fun getCurrentItemPosition() = currentSliderItem

}