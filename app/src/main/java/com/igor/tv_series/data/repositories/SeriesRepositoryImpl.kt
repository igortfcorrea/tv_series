package com.igor.tv_series.data.repositories

import com.igor.tv_series.data.infra.SeriesService
import com.igor.tv_series.data.infra.safeApiCall
import com.igor.tv_series.data.models.EpisodeDto
import com.igor.tv_series.data.models.SearchSerieDto
import com.igor.tv_series.data.models.SeasonDto
import com.igor.tv_series.data.models.SerieDto

internal class SeriesRepositoryImpl(
    private val seriesService: SeriesService
) : SeriesRepository {

    override suspend fun fetchSeries(page: Int): Result<List<SerieDto>> {
        return safeApiCall {
            seriesService.shows(page)
        }
    }

    override suspend fun searchSeries(searchTerm: String): Result<List<SearchSerieDto>> {
        return safeApiCall {
            seriesService.searchShows(searchTerm)
        }
    }

    override suspend fun fetchEpisodes(seasonId: Int): Result<List<EpisodeDto>> {
        return safeApiCall {
            seriesService.episodesBySeason(seasonId)
        }
    }

    override suspend fun fetchSeasons(serieId: Int): Result<List<SeasonDto>> {
        return safeApiCall {
            seriesService.seasons(serieId)
        }
    }
}