package com.igor.tv_series.domain.usecases

import com.igor.tv_series.domain.State
import com.igor.tv_series.domain.models.FavoriteSerieModel

interface FetchFavoriteSeries {
    suspend operator fun invoke(): State<List<FavoriteSerieModel>>
}