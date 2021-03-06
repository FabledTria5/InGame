package com.example.ingame.data.repository

import com.example.ingame.data.db.helper.IDBHelper
import com.example.ingame.data.db.model.HotGame
import com.example.ingame.data.network.api.ApiHelper
import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.network.model.game_developers.GameDevelopers
import com.example.ingame.data.network.model.games_list.GamesList
import com.example.ingame.data.network.model.screenshots.Snapshots
import com.example.ingame.ui.schedulers.ISchedulers
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val dbHelper: IDBHelper,
    private val schedulers: ISchedulers
) : IGamesRepository {

    override fun getHotGames(page: Int, updated: String, pageSize: Int): Single<List<Int>> =
        dbHelper.getHotGames().flatMap {
            if (it.isNullOrEmpty()) {
                apiHelper.getHotGames(page, updated, pageSize)
                    .flatMap { gamesList ->
                        dbHelper.fetchHotGames(DataConverter.convertToHotGames(gamesList.results))
                    }
            } else {
                Single.just(it)
            }
        }.subscribeOn(schedulers.backGround())

    override fun getUpdatedHotGames(page: Int, updated: String, pageSize: Int): Single<List<Int>> =
        dbHelper.clearHotGamesCache().andThen(
            apiHelper.getHotGames(page, updated, pageSize).flatMap { gamesList ->
                dbHelper.fetchHotGames(DataConverter.convertToHotGames(gamesList.results))
            }
        ).subscribeOn(schedulers.backGround())

    override fun getPlatformsList(): Single<List<String>> =
        dbHelper.getPlatformsNames().flatMap {
            if (it.isEmpty()) {
                apiHelper.getPlatforms().flatMap { platforms ->
                    dbHelper.fetchPlatforms(DataConverter.convertToPlatforms(platforms.results))
                }
            } else {
                Single.just(it)
            }
        }.subscribeOn(schedulers.backGround())

    override fun getGamesByPlatform(
        platform: Int,
        dates: String
    ): Single<GamesList> =
        apiHelper.getGamesByPlatform(platform, dates)
            .subscribeOn(schedulers.backGround())

    override fun getNewGamesByPlatform(platform: Int, dates: String): Single<GamesList> =
        apiHelper.getNewGamesByPlatform(platform, dates).subscribeOn(schedulers.backGround())

    override fun getPlatformByName(platformName: String): Single<Int> =
        dbHelper.getPlatformByName(platformName).subscribeOn(schedulers.backGround())

    override fun getGameDetails(id: Int): Single<GameDetails> =
        apiHelper.getGameDetails(id).subscribeOn(schedulers.backGround())

    override fun getSnapshots(gameId: Int): Single<Snapshots> =
        apiHelper.getSnapshots(gameId).subscribeOn(schedulers.backGround())

    override fun getDevelopers(gameId: Int): Single<GameDevelopers> =
        apiHelper.getDevelopers(gameId).subscribeOn(schedulers.backGround())

    override fun getHotGameById(gameId: Int): Single<HotGame> =
        dbHelper.getHotGameById(gameId).subscribeOn(schedulers.backGround())

}