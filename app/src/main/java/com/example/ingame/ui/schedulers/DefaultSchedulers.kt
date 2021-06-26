package com.example.ingame.ui.schedulers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

object DefaultSchedulers : Schedulers {
    override fun backGround(): Scheduler = io.reactivex.rxjava3.schedulers.Schedulers.newThread()

    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}