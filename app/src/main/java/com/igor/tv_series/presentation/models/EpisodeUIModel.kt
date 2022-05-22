package com.igor.tv_series.presentation.models

import android.os.Parcelable
import com.igor.tv_series.domain.models.EpisodeModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodeUIModel(
    val name: String,
    val number: Int,
    val season: Int,
    val summary: String,
    val imageUrl: String? = null
) : Parcelable

fun EpisodeModel.toUIModel(): EpisodeUIModel {
    return EpisodeUIModel(
        name = this.name,
        number = this.number,
        season = this.season,
        summary = this.summary,
        imageUrl = this.imageUrl
    )
}