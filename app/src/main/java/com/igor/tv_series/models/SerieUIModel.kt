package com.igor.tv_series.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SerieUIModel(
    val id: Int,
    val score: Float,
    val name: String,
    val imageUrl: String
) : Parcelable