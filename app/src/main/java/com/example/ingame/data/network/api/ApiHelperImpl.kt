package com.example.ingame.data.network.api

import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {

    override fun getListOfGames(page: Int, updated: String, pageSize: Int) =
        apiService.getListOfGames(page = page, updated = updated, pageSize = pageSize)

    override fun getGamesByPlatform(page: Int, platforms: String) =
        apiService.getGamesByPlatform(page = page, platforms = platforms)

    override fun getGameDetails(id: Int) = apiService.getGameDetails(id = id)

    override fun getSnapshots(gameId: Int) = apiService.getScreenshots(gameId = gameId)

    override fun getDevelopers(gameId: Int) = apiService.getDevelopers(gameId = gameId)

}