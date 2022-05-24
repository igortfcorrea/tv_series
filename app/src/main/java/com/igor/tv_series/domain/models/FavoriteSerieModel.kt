package com.igor.tv_series.domain.models

import com.igor.tv_series.data.entities.FavoriteSeries

data class FavoriteSerieModel(
    val id: Int,
    val rating: Float?,
    val name: String?,
    val imageUrl: String?,
    val premiered: String?,
    val ended: String?,
    val genres: String?,
    val summary: String?
)

fun FavoriteSerieModel.toEntity() = FavoriteSeries(
    id = id,
    rating = rating,
    name = name,
    imageUrl = imageUrl,
    premiered = premiered,
    ended = ended,
    genres = genres,
    summary = summary
)

fun FavoriteSeries.toModel() = FavoriteSerieModel(
    id = id,
    rating = rating,
    name = name,
    imageUrl = imageUrl,
    premiered = premiered,
    ended = ended,
    genres = genres,
    summary = summary
)
