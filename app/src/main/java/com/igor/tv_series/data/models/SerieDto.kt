package com.igor.tv_series.data.models

data class SerieDto(
    val id: Int,
    val rating: RatingDto,
    val name: String,
    val imageUrl: String,
    val premiered: String,
    val ended: String?,
    val genres: List<String>,
    val summary: String
)
