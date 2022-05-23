package com.igor.tv_series.domain.models

import com.igor.tv_series.data.models.SearchSerieDto
import com.igor.tv_series.data.models.SerieDto

data class SerieModel(
    val id: Int,
    val rating: RatingModel?,
    val name: String?,
    val image: ImageModel?,
    val premiered: String?,
    val ended: String?,
    val genres: List<String>?,
    val summary: String?
)

fun SerieDto.toModel() : SerieModel {
    return SerieModel(
        id = this.id,
        rating = this.rating?.toModel(),
        name = this.name,
        image = this.image?.toModel(),
        premiered = this.premiered,
        ended = this.ended,
        genres = this.genres,
        summary = this.summary
    )
}

fun SearchSerieDto.toModel() : SerieModel {
    return SerieModel(
        id = this.show.id,
        rating = this.show.rating?.toModel(),
        name = this.show.name,
        image = this.show.image?.toModel(),
        premiered = this.show.premiered,
        ended = this.show.ended,
        genres = this.show.genres,
        summary = this.show.summary
    )
}

