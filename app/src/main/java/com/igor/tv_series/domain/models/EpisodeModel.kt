package com.igor.tv_series.domain.models

import com.igor.tv_series.data.models.EpisodeDto

data class EpisodeModel(
    val name: String?,
    val number: Int?,
    val season: Int?,
    val summary: String?,
    val image: ImageModel? = null
)

fun EpisodeDto.toModel(): EpisodeModel {
    return EpisodeModel(
        name = this.name,
        number = this.number,
        season = this.season,
        summary = this.summary,
        image = this.image?.toModel()
    )
}