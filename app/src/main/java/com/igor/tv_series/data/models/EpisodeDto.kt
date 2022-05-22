package com.igor.tv_series.data.models

data class EpisodeDto(
    val name: String,
    val number: Int,
    val season: Int,
    val summary: String,
    val imageUrl: String? = null
)
