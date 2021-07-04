package com.example.ingame.data.db.helper

import com.example.ingame.data.db.model.HotGame
import com.example.ingame.data.db.model.Platform
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IDBHelper {
    fun getHotGames(): Single<List<Int>>
    fun getHotGameById(id: Int): Single<HotGame>
    fun getPlatformsNames(): Single<List<String>>

    fun fetchHotGames(hotGames: List<HotGame>): Single<List<Int>>
    fun fetchPlatforms(platforms: List<Platform>): Single<List<String>>

    fun clearHotGamesCache(): Completable
}