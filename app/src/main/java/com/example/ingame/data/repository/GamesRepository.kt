package com.example.ingame.data.repository

import com.example.ingame.data.db.helper.IDBHelper
import com.example.ingame.data.db.model.HotGame
import com.example.ingame.data.network.api.ApiHelper
import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.network.model.game_developers.GameDevelopers
import com.example.ingame.data.network.model.games_list.GamesList
import com.example.ingame.data.network.model.platforms.Platforms
import com.example.ingame.data.network.model.screenshots.Snapshots
import com.example.ingame.ui.schedulers.Schedulers
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val dbHelper: IDBHelper,
    private val schedulers: Schedulers
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
        }.subscribeOn(schedulers.backGround())

    override fun getNewListOfGames(page: Int, updated: String, pageSize: Int): Single<List<Int>> =
        dbHelper.clearHotGamesCache().andThen(
            apiHelper.getListOfGames(page, updated, pageSize).flatMap { gamesList ->
                dbHelper.fetchHotGames(dataConverter.convertToHotGames(gamesList.results))
            }
        ).subscribeOn(schedulers.backGround())

    override fun getPlatformsList(): Single<Platforms> =
        apiHelper.getPlatforms().subscribeOn(schedulers.backGround())

    override fun getGamesByPlatform(page: Int, platforms: String): Single<GamesList> =
        apiHelper.getGamesByPlatform(page, platforms)
            .subscribeOn(schedulers.backGround())

    override fun getGameDetails(id: Int): Single<GameDetails> =
        apiHelper.getGameDetails(id).subscribeOn(schedulers.backGround())

    override fun getSnapshots(gameId: Int): Single<Snapshots> =
        apiHelper.getSnapshots(gameId).subscribeOn(schedulers.backGround())

    override fun getDevelopers(gameId: Int): Single<GameDevelopers> =
        apiHelper.getDevelopers(gameId).subscribeOn(schedulers.backGround())

    override fun getHotGameById(gameId: Int): Single<HotGame> =
        dbHelper.getHotGameById(gameId).subscribeOn(schedulers.backGround())

}