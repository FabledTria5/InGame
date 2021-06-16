package com.example.ingame.data.network.api

import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {

    override fun getListOfGames(page: Int, updated: String) =
        apiService.getListOfGames(page = page, updated = updated)

    override fun getGamesByPlatform(page: Int, platforms: String) =
        apiService.getGamesByPlatform(page, platforms)

}