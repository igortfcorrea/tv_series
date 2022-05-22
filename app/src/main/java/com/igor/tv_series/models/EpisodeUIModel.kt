package com.igor.tv_series.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodeUIModel(
    val name: String,
    val number: Int,
    val season: Int,
    val summary: String,
    val imageUrl: String? = null
) : Parcelable
