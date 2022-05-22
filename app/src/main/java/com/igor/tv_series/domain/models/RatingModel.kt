package com.igor.tv_series.domain.models

import com.igor.tv_series.data.models.RatingDto

data class RatingModel(
    val average: Float
)

fun RatingDto.toModel(): RatingModel {
    return RatingModel(
        average = this.average
    )
}