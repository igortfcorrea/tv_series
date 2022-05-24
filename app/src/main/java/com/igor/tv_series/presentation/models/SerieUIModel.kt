package com.igor.tv_series.presentation.models

import android.os.Parcelable
import com.igor.tv_series.domain.models.FavoriteSerieModel
import com.igor.tv_series.domain.models.ImageModel
import com.igor.tv_series.domain.models.RatingModel
import com.igor.tv_series.domain.models.SerieModel
import com.igor.tv_series.presentation.helpers.toPercent
import kotlinx.parcelize.Parcelize

@Parcelize
data class SerieUIModel(
    val id: Int,
    val score: Float,
    val name: String,
    val imageUrl: String,
    val premiered: String,
    val ended: String?,
    val genres: String,
    val summary: String,
    var isFavorite: Boolean = false
) : Parcelable

fun SerieModel.toUIModel(): SerieUIModel {
    return SerieUIModel(
        id = this.id,
        score = this.rating?.average ?: 0f,
        name = this.name ?: "",
        imageUrl = this.image?.medium ?: "",
        premiered = "Premiered: ${this.premiered}",
        ended = "Ended: ${this.ended}",
        genres = this.genres?.joinToString() ?: "",
        summary = this.summary ?: ""
    )
}

fun FavoriteSerieModel.toUIModel(): SerieUIModel {
    return SerieUIModel(
        id = this.id,
        score = this.rating ?: 0f,
        name = this.name ?: "",
        imageUrl = this.imageUrl ?: "",
        premiered = this.premiered ?: "",
        ended = this.ended,
        genres = this.genres ?: "",
        summary = this.summary ?: ""
    )
}

fun SerieUIModel.toModel(): FavoriteSerieModel {
    return FavoriteSerieModel(
        id = this.id,
        rating = this.score,
        name = this.name,
        imageUrl = this.imageUrl,
        premiered = this.premiered,
        ended = this.ended,
        genres = this.genres,
        summary = this.summary
    )
}