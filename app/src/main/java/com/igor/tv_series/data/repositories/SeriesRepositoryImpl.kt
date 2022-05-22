package com.igor.tv_series.data.repositories

import com.igor.tv_series.data.infra.SeriesService
import com.igor.tv_series.data.models.EpisodeDto
import com.igor.tv_series.data.models.SerieDto

internal class SeriesRepositoryImpl(
    private val seriesService: SeriesService
) : SeriesRepository {

    override suspend fun fetchSeries(page: Int): List<SerieDto> {
        return seriesService.shows(page)
    }

    override suspend fun fetchEpisodes(serieId: Int): List<EpisodeDto> {
        return seriesService.episodes(serieId)
    }
}