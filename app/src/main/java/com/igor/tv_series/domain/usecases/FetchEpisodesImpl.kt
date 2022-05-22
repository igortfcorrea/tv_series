package com.igor.tv_series.domain.usecases

import com.igor.tv_series.data.repositories.SeriesRepository
import com.igor.tv_series.domain.Empty
import com.igor.tv_series.domain.Error
import com.igor.tv_series.domain.Loaded
import com.igor.tv_series.domain.State
import com.igor.tv_series.domain.models.EpisodeModel
import com.igor.tv_series.domain.models.toModel

internal class FetchEpisodesImpl(
    private val seriesRepository: SeriesRepository
) : FetchEpisodes {

    override suspend fun invoke(serieId: Int): State<List<EpisodeModel>> {
        val fetchEpisodesResult = seriesRepository.fetchEpisodes(serieId)

        return if (fetchEpisodesResult.isSuccess) {
            fetchEpisodesResult.getOrNull()?.map { episodeDto ->
                episodeDto.toModel()
            }?.let { episodes ->
                Loaded(episodes)
            } ?: run {
                Empty()
            }
        } else {
            Error(fetchEpisodesResult.exceptionOrNull()?.message)
        }
    }

}