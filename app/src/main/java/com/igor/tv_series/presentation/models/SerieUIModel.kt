package com.igor.tv_series.presentation.models

import android.os.Parcelable
import com.igor.tv_series.domain.models.SerieModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class SerieUIModel(
    val id: Int,
    val score: Float,
    val name: String,
    val imageUrl: String,
    val premiered: String,
    val ended: String?,
    val genres: List<String>,
    val summary: String
) : Parcelable

fun SerieModel.toUIModel(): SerieUIModel {
    return SerieUIModel(
        id = this.id,
        score = this.rating.average,
        name = this.name,
        imageUrl = this.image.medium,
        premiered = this.premiered,
        ended = this.ended,
        genres = this.genres,
        summary = this.summary
    )
}