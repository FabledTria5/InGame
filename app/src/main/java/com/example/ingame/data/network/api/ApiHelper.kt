package com.example.ingame.data.network.api

import com.example.ingame.data.network.model.games_list.GamesList
import io.reactivex.rxjava3.core.Single

interface ApiHelper {
    fun getListOfGames(page: Int, updated: String): Single<GamesList>
    fun getGamesByPlatform(page: Int, platforms: String): Single<GamesList>
}