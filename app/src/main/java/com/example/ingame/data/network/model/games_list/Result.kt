package com.example.ingame.data.network.model.games_list

data class Result(
    val added: Int,
    val background_image: String,
    val esrb_rating: EsrbRating,
    val id: Int,
    val metacritic: Int,
    val name: String,
    val platforms: List<Platforms>,
    val playtime: Int,
    val rating: Double,
    val rating_top: Int,
    val ratings_count: Int,
    val released: String,
    val reviews_text_count: String,
    val slug: String,
    val suggestions_count: Int,
    val tba: Boolean,
    val tags: List<Tag>,
    val updated: String
)