package com.example.ingame.data.network.repository

import com.example.ingame.data.network.api.ApiHelper
import com.example.ingame.data.network.model.games_list.GamesList
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RetrofitRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper
) : RetrofitRepository {

    override fun getListOfGames(page: Int, updated: String): Single<GamesList> =
        apiHelper.getListOfGames(page, updated)
            .subscribeOn(Schedulers.io())

    override fun getGamesByPlatform(page: Int, platforms: String): Single<GamesList> =
        apiHelper.getGamesByPlatform(page, platforms)
            .subscribeOn(Schedulers.io())

}