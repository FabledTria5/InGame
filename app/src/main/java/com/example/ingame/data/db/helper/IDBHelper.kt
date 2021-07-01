package com.example.ingame.data.db.helper

import com.example.ingame.data.db.model.HotGame
import io.reactivex.rxjava3.core.Single

interface IDBHelper {
    fun getHotGames(): Single<List<Int>>
    fun getHotGameById(id: Int): Single<HotGame>

    fun fetchHotGames(hotGames: List<HotGame>): Single<List<Int>>
}