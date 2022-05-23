package com.igor.tv_series.domain.models

import com.igor.tv_series.data.models.SeasonDto

data class SeasonModel(
    val id: Int,
    val number: Int
)

fun SeasonDto.toModel(): SeasonModel {
    return SeasonModel(
        id = id,
        number = number
    )
}
