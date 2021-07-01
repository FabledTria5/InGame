package com.example.ingame.data.repository

import com.example.ingame.data.db.model.HotGame
import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.network.model.game_developers.GameDevelopers
import com.example.ingame.data.network.model.games_list.GamesList
import com.example.ingame.data.network.model.screenshots.Snapshots
import io.reactivex.rxjava3.core.Single

interface IGamesRepository {
    fun getListOfGames(page: Int, updated: String, pageSize: Int): Single<List<Int>>
    fun getGamesByPlatform(page: Int, platforms: String): Single<GamesList>
    fun getGameDetails(id: Int): Single<GameDetails>
    fun getSnapshots(gameId: Int): Single<Snapshots>
    fun getDevelopers(gameId: Int): Single<GameDevelopers>
    fun getHotGameById(gameId: Int): Single<HotGame>
}