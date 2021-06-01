package com.example.ingame.ui.fragments.home

import com.example.ingame.utils.Constants
import java.util.*

class HomeModel(private var timerListener: TimerListener? = null) {

    private lateinit var timer: Timer

    private var previousTab = 0

    fun getPreviousTab() = previousTab

    fun updateTab(newTab: Int) {
        previousTab = newTab
    }

    fun addTimerListener(timerListener: TimerListener) {
        this.timerListener = timerListener
    }

    fun startTimer() {
        timer = Timer().also {
            it.schedule(object : TimerTask() {
                override fun run() {
                    timerListener?.onTimerTick()
                }
            }, Constants.HOT_GAMES_DELAY_TICK_RATE, Constants.HOT_GAMES_DELAY_TICK_RATE)
        }
    }

    fun stopTimer() = timer.cancel()

}