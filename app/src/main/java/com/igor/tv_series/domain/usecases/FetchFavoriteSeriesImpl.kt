package com.igor.tv_series.domain.usecases

import com.igor.tv_series.data.repositories.SeriesRepository
import com.igor.tv_series.domain.Empty
import com.igor.tv_series.domain.Error
import com.igor.tv_series.domain.State
import com.igor.tv_series.domain.Success
import com.igor.tv_series.domain.models.FavoriteSerieModel
import com.igor.tv_series.domain.models.toModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchFavoriteSeriesImpl(
    private val seriesRepository: SeriesRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : FetchFavoriteSeries {

    override suspend fun invoke(): State<List<FavoriteSerieModel>> {
        val fetchFavoriteSeries = withContext(ioDispatcher) {
            seriesRepository.fetchFavoriteSeries()
        }

        return if (fetchFavoriteSeries.isSuccess) {
            fetchFavoriteSeries.getOrNull()?.map { serieDto ->
                serieDto.toModel()
            }?.let { series ->
                Success(series)
            } ?: run {
                Empty()
            }
        } else {
            Error(fetchFavoriteSeries.exceptionOrNull()?.message)
        }
    }

}