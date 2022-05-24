package com.igor.tv_series.domain.usecases

import com.igor.tv_series.data.repositories.SeriesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IsAFavoriteSerieImpl(
    private val seriesRepository: SeriesRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IsAFavoriteSerie {

    override suspend fun invoke(id: Int): Boolean {
        return withContext(ioDispatcher) {
            seriesRepository.isAFavoriteSerie(id)
        }
    }

}