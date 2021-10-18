package com.example.ingame.ui.schedulers

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

object TestingSchedulers : ISchedulers {

    override fun backGround(): Scheduler = Schedulers.trampoline()

    override fun main(): Scheduler = Schedulers.trampoline()

}