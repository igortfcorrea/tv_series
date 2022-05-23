package com.igor.tv_series.presentation.models

import com.igor.tv_series.domain.models.SeasonModel

data class SeasonUIModel(
    val id: Int,
    val number: String
)

fun SeasonModel.toUIModel(): SeasonUIModel {
    return SeasonUIModel(
        id = id,
        number = "Season $number"
    )
}
