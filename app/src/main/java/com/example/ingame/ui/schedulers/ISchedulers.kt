package com.example.ingame.ui.schedulers

import io.reactivex.rxjava3.core.Scheduler

interface ISchedulers {
    fun backGround(): Scheduler
    fun main(): Scheduler
}