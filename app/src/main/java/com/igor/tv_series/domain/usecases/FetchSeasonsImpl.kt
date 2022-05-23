package com.igor.tv_series.domain.usecases

import com.igor.tv_series.data.repositories.SeriesRepository
import com.igor.tv_series.domain.Empty
import com.igor.tv_series.domain.Error
import com.igor.tv_series.domain.State
import com.igor.tv_series.domain.Success
import com.igor.tv_series.domain.models.SeasonModel
import com.igor.tv_series.domain.models.toModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class FetchSeasonsImpl(
    private val seriesRepository: SeriesRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : FetchSeasons {

    override suspend fun invoke(serieId: Int): State<List<SeasonModel>> {
        val fetchSeasonsResult = withContext(ioDispatcher) {
            seriesRepository.fetchSeasons(serieId)
        }

        return if (fetchSeasonsResult.isSuccess) {
            fetchSeasonsResult.getOrNull()?.map { episodeDto ->
                episodeDto.toModel()
            }?.let { episodes ->
                Success(episodes)
            } ?: run {
                Empty()
            }
        } else {
            Error(fetchSeasonsResult.exceptionOrNull()?.message)
        }
    }

}