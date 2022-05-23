package com.igor.tv_series.domain.usecases

import com.igor.tv_series.data.repositories.SeriesRepository
import com.igor.tv_series.domain.Empty
import com.igor.tv_series.domain.Error
import com.igor.tv_series.domain.State
import com.igor.tv_series.domain.Success
import com.igor.tv_series.domain.models.SeasonModel
import com.igor.tv_series.domain.models.toModel

internal class FetchSeasonsImpl(
    private val seriesRepository: SeriesRepository
) : FetchSeasons {

    override suspend fun invoke(serieId: Int): State<List<SeasonModel>> {
        val fetchSeasonsResult = seriesRepository.fetchSeasons(serieId)

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