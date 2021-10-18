package com.example.ingame.data.repository

import com.example.ingame.data.db.model.HotGame
import com.example.ingame.data.db.model.Platform
import com.example.ingame.data.network.model.games_list.Result
import com.example.ingame.data.network.model.platforms.PlatformsResult
import com.example.ingame.utils.getNamesWhile

object DataConverter {

    fun convertToHotGames(results: List<Result>) = results
        .filter { it.background_image != null }
        .map { result ->
            HotGame(
                gameId = result.id,
                gameImage = result.background_image!!,
                gameTitle = result.name,
                gameRating = result.esrb_rating?.name ?: "",
                gameReleaseYear = result.released?.take(4) ?: "",
                gamePlatforms = result.platforms.getNamesWhile(3)
            )
        }

    fun convertToPlatforms(platformsResult: List<PlatformsResult>) = platformsResult.map { result ->
        Platform(result.id, result.name)
    }
}