package com.example.ingame.data.network.model.game_detail

import com.example.ingame.data.network.model.common.EsrbRating
import com.example.ingame.data.network.model.common.Platforms
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GameDetails(
    @SerializedName("achievements_count")
    val achievements_count: Int,
    @SerializedName("added")
    val added: Int,
    @SerializedName("additions_count")
    val additions_count: Int,
    @SerializedName("alternative_names")
    val alternative_names: List<String>,
    @SerializedName("background_image")
    val background_image: String,
    @SerializedName("background_image_additional")
    val background_image_additional: String,
    @SerializedName("creators_count")
    val creators_count: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("esrb_rating")
    val esrb_rating: EsrbRating,
    @SerializedName("game_series_count")
    val game_series_count: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("developers")
    val developers: List<Developer>,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("publishers")
    val publishers: List<Publisher>,
    @SerializedName("metacritic")
    val metacritic: Int,
    @SerializedName("metacritic_platforms")
    val metacritic_platforms: List<MetacriticPlatform>,
    @SerializedName("metacritic_url")
    val metacritic_url: String,
    @SerializedName("movies_count")
    val movies_count: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("name_original")
    val name_original: String,
    @SerializedName("parent_achievements_count")
    val parent_achievements_count: String,
    @SerializedName("parents_count")
    val parents_count: Int,
    @SerializedName("platforms")
    val platforms: List<Platforms>,
    @SerializedName("playtime")
    val playtime: Int,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("rating_top")
    val rating_top: Int,
    @SerializedName("ratings_count")
    val ratings_count: Int,
    @SerializedName("reddit_count")
    val reddit_count: Int,
    @SerializedName("reddit_description")
    val reddit_description: String,
    @SerializedName("reddit_logo")
    val reddit_logo: String,
    @SerializedName("reddit_name")
    val reddit_name: String,
    @SerializedName("reddit_url")
    val reddit_url: String,
    @SerializedName("released")
    val released: String,
    @SerializedName("reviews_text_count")
    val reviews_text_count: String,
    @SerializedName("screenshots_count")
    val screenshots_count: Int,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("suggestions_count")
    val suggestions_count: Int,
    @SerializedName("tba")
    val tba: Boolean,
    @SerializedName("twitch_count")
    val twitch_count: String,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("youtube_count")
    val youtube_count: String
) : Serializable