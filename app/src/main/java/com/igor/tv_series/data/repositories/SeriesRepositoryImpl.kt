package com.igor.tv_series.data.repositories

import com.igor.tv_series.data.entities.FavoriteSeries
import com.igor.tv_series.data.infra.FavoriteSeriesDao
import com.igor.tv_series.data.infra.SeriesService
import com.igor.tv_series.data.infra.safeApiCall
import com.igor.tv_series.data.infra.safeRoomCall
import com.igor.tv_series.data.models.EpisodeDto
import com.igor.tv_series.data.models.SearchSerieDto
import com.igor.tv_series.data.models.SeasonDto
import com.igor.tv_series.data.models.SerieDto
import java.lang.Exception

internal class SeriesRepositoryImpl(
    private val seriesService: SeriesService,
    private val favoriteSeriesDao: FavoriteSeriesDao
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

    override suspend fun fetchFavoriteSeries(): Result<List<FavoriteSeries>> {
        return safeRoomCall {
            favoriteSeriesDao.getAll()
        }
    }

    override suspend fun insertAll(favoriteSeries: List<FavoriteSeries>) {
        return favoriteSeriesDao.insertAll(favoriteSeries)
    }

    override suspend fun delete(favoriteSeries: List<FavoriteSeries>) {
        return favoriteSeriesDao.delete(favoriteSeries)
    }
}