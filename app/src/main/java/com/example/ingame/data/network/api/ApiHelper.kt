package com.example.ingame.data.network.api

import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.network.model.games_list.GamesList
import io.reactivex.rxjava3.core.Single

interface ApiHelper {
    fun getListOfGames(page: Int, updated: String, pageSize: Int): Single<GamesList>
    fun getGamesByPlatform(page: Int, platforms: String): Single<GamesList>
    fun getGameDetails(id: Int): Single<GameDetails>
}