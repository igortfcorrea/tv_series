package com.igor.tv_series.domain.models

import com.igor.tv_series.data.models.SerieDto

data class SerieModel(
    val id: Int,
    val rating: RatingModel,
    val name: String,
    val image: ImageModel,
    val premiered: String,
    val ended: String?,
    val genres: List<String>,
    val summary: String
)

fun SerieDto.toModel() : SerieModel {
    return SerieModel(
        id = this.id,
        rating = this.rating.toModel(),
        name = this.name,
        image = this.image.toModel(),
        premiered = this.premiered,
        ended = this.ended,
        genres = this.genres,
        summary = this.summary
    )
}