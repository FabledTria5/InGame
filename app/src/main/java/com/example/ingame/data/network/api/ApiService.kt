package com.example.ingame.data.network.api

import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.network.model.game_developers.GameDevelopers
import com.example.ingame.data.network.model.games_list.GamesList
import com.example.ingame.data.network.model.platforms.Platforms
import com.example.ingame.data.network.model.screenshots.Snapshots
import com.example.ingame.utils.Constants.HOME_GAMES_LIST_SIZE
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(value = "api/games")
    fun getHotGames(
        @Query(value = "page") page: Int,
        @Query(value = "updated", encoded = true) updated: String,
        @Query(value = "page_size") pageSize: Int,
        @Query(value = "ordering") ordering: String = "-rating"
    ): Single<GamesList>

    @GET(value = "api/games")
    fun getPopularGamesByPlatform(
        @Query(value = "platforms") platform: Int,
        @Query(value = "dates") dates: String,
        @Query(value = "page_size") pageSize: Int = HOME_GAMES_LIST_SIZE,
        @Query(value = "ordering") ordering: String = "-added"
    ): Single<GamesList>

    @GET(value = "getNewGamesByPlatform")
    fun getNewGamesByPlatform(
        @Query(value = "platforms") platform: Int,
        @Query(value = "dates") dates: String,
        @Query(value = "page_size") pageSize: Int = HOME_GAMES_LIST_SIZE,
    ): Single<GamesList>

    @GET(value = "api/games/{id}")
    fun getGameDetails(
        @Path(value = "id") id: Int,
    ): Single<GameDetails>

    @GET(value = "api/games/{game_pk}/screenshots")
    fun getScreenshots(
        @Path(value = "game_pk") gameId: Int,
    ): Single<Snapshots>

    @GET(value = "api/games/{game_pk}/development-team")
    fun getDevelopers(
        @Path(value = "game_pk") gameId: Int,
    ): Single<GameDevelopers>

    @GET(value = "api/platforms")
    fun getPlatformsList(
        @Query(value = "page_size") pageSize: Int = 15
    ): Single<Platforms>

    @GET(value = "api/games/")
    fun searchGames(
        @Query(value = "search") query: String,
        @Query(value = "page") page: Int = 0,
        @Query(value = "ordering") ordering: String = "-released"
    ): Single<GamesList>
}