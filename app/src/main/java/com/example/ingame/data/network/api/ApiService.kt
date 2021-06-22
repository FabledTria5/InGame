package com.example.ingame.data.network.api

import com.example.ingame.BuildConfig
import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.network.model.games_list.GamesList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(value = "api/games")
    fun getListOfGames(
        @Query(value = "key", encoded = true) apiKey: String = BuildConfig.GAMES_API_KEY,
        @Query(value = "page") page: Int,
        @Query(value = "updated", encoded = true) updated: String,
        @Query(value = "page_size") pageSize: Int
    ): Single<GamesList>

    @GET(value = "api/games")
    fun getGamesByPlatform(
        @Query(value = "page") page: Int,
        @Query(value = "platforms") platforms: String,
        @Query(value = "key") apiKey: String = BuildConfig.GAMES_API_KEY
    ): Single<GamesList>

    @GET(value = "api/games/{id}")
    fun getGameDetails(
        @Path(value = "id") id: Int,
        @Query(value = "key") apiKey: String = BuildConfig.GAMES_API_KEY
    ): Single<GameDetails>

}