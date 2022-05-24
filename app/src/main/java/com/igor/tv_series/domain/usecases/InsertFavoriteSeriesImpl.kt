package com.igor.tv_series.domain.usecases

import com.igor.tv_series.data.repositories.SeriesRepository
import com.igor.tv_series.domain.models.FavoriteSerieModel
import com.igor.tv_series.domain.models.toEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InsertFavoriteSeriesImpl(
    private val seriesRepository: SeriesRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : InsertFavoriteSeries {

    override suspend fun invoke(favoriteSeries: List<FavoriteSerieModel>) {
        withContext(ioDispatcher) {
            seriesRepository.insertFavoriteSeries(favoriteSeries.map { it.toEntity() })
        }
    }

}