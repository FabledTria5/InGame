package com.example.ingame.data.network.repository

import com.example.ingame.data.network.api.ApiHelper
import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.network.model.game_developers.GameDevelopers
import com.example.ingame.data.network.model.games_list.GamesList
import com.example.ingame.data.network.model.screenshots.Snapshots
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RetrofitRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper
) : RetrofitRepository {

    override fun getListOfGames(page: Int, updated: String, pageSize: Int): Single<GamesList> =
        apiHelper.getListOfGames(page, updated, pageSize)
            .subscribeOn(Schedulers.io())

    override fun getGamesByPlatform(page: Int, platforms: String): Single<GamesList> =
        apiHelper.getGamesByPlatform(page, platforms)
            .subscribeOn(Schedulers.io())

    override fun getGameDetails(id: Int): Single<GameDetails> =
        apiHelper.getGameDetails(id).subscribeOn(Schedulers.io())

    override fun getSnapshots(gameId: Int): Single<Snapshots> =
        apiHelper.getSnapshots(gameId).subscribeOn(Schedulers.io())

    override fun getDevelopers(gameId: Int): Single<GameDevelopers> =
        apiHelper.getDevelopers(gameId).subscribeOn(Schedulers.io())

}