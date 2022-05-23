package com.igor.tv_series.domain.usecases

import com.igor.tv_series.data.repositories.SeriesRepository
import com.igor.tv_series.domain.Empty
import com.igor.tv_series.domain.Error
import com.igor.tv_series.domain.State
import com.igor.tv_series.domain.Success
import com.igor.tv_series.domain.models.SerieModel
import com.igor.tv_series.domain.models.toModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class SearchSeriesImpl(
    private val seriesRepository: SeriesRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : SearchSeries {

    override suspend fun invoke(term: String): State<List<SerieModel>> {
        val searchSeriesResult = withContext(ioDispatcher) {
            seriesRepository.searchSeries(term)
        }

        return if (searchSeriesResult.isSuccess) {
            searchSeriesResult.getOrNull()?.map { serieDto ->
                serieDto.toModel()
            }?.let { series ->
                Success(series)
            } ?: run {
                Empty()
            }
        } else {
            Error(searchSeriesResult.exceptionOrNull()?.message)
        }
    }

}