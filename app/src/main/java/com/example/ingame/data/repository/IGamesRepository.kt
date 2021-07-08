package com.example.ingame.data.repository

import com.example.ingame.data.db.model.HotGame
import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.network.model.game_developers.GameDevelopers
import com.example.ingame.data.network.model.games_list.GamesList
import com.example.ingame.data.network.model.screenshots.Snapshots
import io.reactivex.rxjava3.core.Single

interface IGamesRepository {

    fun getHotGames(page: Int, updated: String, pageSize: Int): Single<List<Int>>
    fun getUpdatedHotGames(page: Int, updated: String, pageSize: Int): Single<List<Int>>
    fun getGamesByPlatform(platform: Int, dates: String): Single<GamesList>
    fun getNewGamesByPlatform(platform: Int, dates: String) : Single<GamesList>
    fun getHotGameById(gameId: Int): Single<HotGame>

    fun getPlatformsList(): Single<List<String>>
    fun getPlatformByName(platformName: String): Single<Int>

    fun getGameDetails(id: Int): Single<GameDetails>
    fun getSnapshots(gameId: Int): Single<Snapshots>
    fun getDevelopers(gameId: Int): Single<GameDevelopers>

}