package com.example.ingame.data.network.api

import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.network.model.game_developers.GameDevelopers
import com.example.ingame.data.network.model.games_list.GamesList
import com.example.ingame.data.network.model.platforms.Platforms
import com.example.ingame.data.network.model.screenshots.Snapshots
import io.reactivex.rxjava3.core.Single

interface ApiHelper {
    fun getHotGames(page: Int, updated: String, pageSize: Int): Single<GamesList>
    fun getGamesByPlatform(page: Int, platform: Int, dates: String): Single<GamesList>
    fun getGameDetails(id: Int): Single<GameDetails>
    fun getSnapshots(gameId: Int): Single<Snapshots>
    fun getDevelopers(gameId: Int): Single<GameDevelopers>

    fun getPlatforms(): Single<Platforms>
}