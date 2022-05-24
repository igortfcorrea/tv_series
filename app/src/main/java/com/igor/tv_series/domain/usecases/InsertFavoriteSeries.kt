package com.igor.tv_series.domain.usecases

import com.igor.tv_series.domain.models.FavoriteSerieModel

interface InsertFavoriteSeries {
    suspend operator fun invoke(favoriteSeries: List<FavoriteSerieModel>)
}