package com.igor.tv_series.domain.usecases

import com.igor.tv_series.data.repositories.SeriesRepository
import com.igor.tv_series.domain.Empty
import com.igor.tv_series.domain.Error
import com.igor.tv_series.domain.Success
import com.igor.tv_series.domain.State
import com.igor.tv_series.domain.models.FavoriteSerieModel
import com.igor.tv_series.domain.models.SerieModel
import com.igor.tv_series.domain.models.toModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class FetchSeriesImpl(
    private val seriesRepository: SeriesRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : FetchSeries {

    private var currentPage = 0

    override suspend fun invoke(): State<List<SerieModel>> {
        val fetchSeriesResult = withContext(ioDispatcher) {
            seriesRepository.fetchSeries(currentPage)
        }

        return if (fetchSeriesResult.isSuccess) {
            this.currentPage++

            fetchSeriesResult.getOrNull()?.map { serieDto ->
                serieDto.toModel()
            }?.let { series ->
                Success(series)
            } ?: run {
                Empty()
            }
        } else {
            Error(fetchSeriesResult.exceptionOrNull()?.message)
        }
    }

    override suspend fun onSearched() {
        this.currentPage = 0
    }
}