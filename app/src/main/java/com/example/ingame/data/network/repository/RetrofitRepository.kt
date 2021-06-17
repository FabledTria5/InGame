package com.example.ingame.data.network.repository

import com.example.ingame.data.network.model.games_list.GamesList
import io.reactivex.rxjava3.core.Single

interface RetrofitRepository {
    fun getListOfGames(page: Int, updated: String, pageSize: Int): Single<GamesList>
    fun getGamesByPlatform(page: Int, platforms: String): Single<GamesList>
}