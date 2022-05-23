package com.igor.tv_series.data.repositories

import com.igor.tv_series.data.models.EpisodeDto
import com.igor.tv_series.data.models.SearchSerieDto
import com.igor.tv_series.data.models.SerieDto

interface SeriesRepository {

    suspend fun fetchSeries(page: Int): Result<List<SerieDto>>

    suspend fun searchSeries(searchTerm: String): Result<List<SearchSerieDto>>

    suspend fun fetchEpisodes(serieId: Int): Result<List<EpisodeDto>>

}