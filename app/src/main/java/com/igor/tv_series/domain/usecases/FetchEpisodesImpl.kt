package com.igor.tv_series.domain.usecases

import com.igor.tv_series.data.repositories.SeriesRepository
import com.igor.tv_series.domain.Empty
import com.igor.tv_series.domain.Error
import com.igor.tv_series.domain.Success
import com.igor.tv_series.domain.State
import com.igor.tv_series.domain.models.EpisodeModel
import com.igor.tv_series.domain.models.toModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class FetchEpisodesImpl(
    private val seriesRepository: SeriesRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : FetchEpisodes {

    override suspend fun invoke(seasonId: Int): State<List<EpisodeModel>> {
        val fetchEpisodesResult = withContext(ioDispatcher) {
            seriesRepository.fetchEpisodes(seasonId)
        }

        return if (fetchEpisodesResult.isSuccess) {
            fetchEpisodesResult.getOrNull()?.map { episodeDto ->
                episodeDto.toModel()
            }?.let { episodes ->
                Success(episodes)
            } ?: run {
                Empty()
            }
        } else {
            Error(fetchEpisodesResult.exceptionOrNull()?.message)
        }
    }

}