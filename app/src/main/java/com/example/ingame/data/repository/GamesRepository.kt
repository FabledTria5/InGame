package com.example.ingame.data.repository

import com.example.ingame.data.db.helper.IDBHelper
import com.example.ingame.data.db.model.HotGame
import com.example.ingame.data.network.api.ApiHelper
import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.network.model.game_developers.GameDevelopers
import com.example.ingame.data.network.model.games_list.GamesList
import com.example.ingame.data.network.model.screenshots.Snapshots
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val dbHelper: IDBHelper
) : IGamesRepository {

    private val dataConverter = DataConverter

    override fun getListOfGames(page: Int, updated: String, pageSize: Int): Single<List<Int>> =
        dbHelper.getHotGames().flatMap {
            if (it.isNullOrEmpty()) {
                apiHelper.getListOfGames(page, updated, pageSize)
                    .flatMap { gamesList ->
                        dbHelper.fetchHotGames(dataConverter.convertToHotGames(gamesList.results))
                    }
            } else {
                Single.just(it)
            }
        }.subscribeOn(Schedulers.io())

    override fun getGamesByPlatform(page: Int, platforms: String): Single<GamesList> =
        apiHelper.getGamesByPlatform(page, platforms)
            .subscribeOn(Schedulers.io())

    override fun getGameDetails(id: Int): Single<GameDetails> =
        apiHelper.getGameDetails(id).subscribeOn(Schedulers.io())

    override fun getSnapshots(gameId: Int): Single<Snapshots> =
        apiHelper.getSnapshots(gameId).subscribeOn(Schedulers.io())

    override fun getDevelopers(gameId: Int): Single<GameDevelopers> =
        apiHelper.getDevelopers(gameId).subscribeOn(Schedulers.io())

    override fun getHotGameById(gameId: Int): Single<HotGame> =
        dbHelper.getHotGameById(gameId).subscribeOn(Schedulers.io())

}