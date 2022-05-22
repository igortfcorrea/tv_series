package com.igor.tv_series.presentation.models

import android.os.Parcelable
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