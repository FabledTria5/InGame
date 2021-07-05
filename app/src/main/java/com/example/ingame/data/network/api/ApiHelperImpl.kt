package com.example.ingame.data.network.api

import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {

    override fun getHotGames(page: Int, updated: String, pageSize: Int) =
        apiService.getHotGames(page = page, updated = updated, pageSize = pageSize)

    override fun getGamesByPlatform(page: Int, platform: Int, dates: String) =
        apiService.getPopularGamesByPlatform(platform = platform, dates = dates)

    override fun getGameDetails(id: Int) = apiService.getGameDetails(id = id)

    override fun getSnapshots(gameId: Int) = apiService.getScreenshots(gameId = gameId)

    override fun getDevelopers(gameId: Int) = apiService.getDevelopers(gameId = gameId)

    override fun getPlatforms() = apiService.getPlatformsList()

}