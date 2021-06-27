package com.example.ingame.data.network.model.game_detail

import com.example.ingame.data.network.model.common.EsrbRating
import com.example.ingame.data.network.model.common.Platforms
import java.io.Serializable

data class GameDetails(
    val achievements_count: Int,
    val added: Int,
    val additions_count: Int,
    val alternative_names: List<String>,
    val background_image: String,
    val background_image_additional: String,
    val creators_count: Int,
    val description: String,
    val esrb_rating: EsrbRating,
    val game_series_count: Int,
    val id: Int,
    val genres: List<Genre>,
    val publishers: List<Publisher>,
    val metacritic: Int,
    val metacritic_platforms: List<MetacriticPlatform>,
    val metacritic_url: String,
    val movies_count: Int,
    val name: String,
    val name_original: String,
    val parent_achievements_count: String,
    val parents_count: Int,
    val platforms: List<Platforms>,
    val playtime: Int,
    val rating: Double,
    val rating_top: Int,
    val ratings_count: Int,
    val reddit_count: Int,
    val reddit_description: String,
    val reddit_logo: String,
    val reddit_name: String,
    val reddit_url: String,
    val released: String,
    val reviews_text_count: String,
    val screenshots_count: Int,
    val slug: String,
    val suggestions_count: Int,
    val tba: Boolean,
    val twitch_count: String,
    val updated: String,
    val website: String,
    val youtube_count: String
) : Serializable