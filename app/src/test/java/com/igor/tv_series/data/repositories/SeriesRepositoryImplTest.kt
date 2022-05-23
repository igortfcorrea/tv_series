package com.igor.tv_series.data.repositories

import com.igor.tv_series.data.entities.FavoriteSeries
import com.igor.tv_series.data.infra.FavoriteSeriesDao
import com.igor.tv_series.data.infra.SeriesService
import com.igor.tv_series.data.models.EpisodeDto
import com.igor.tv_series.data.models.SearchSerieDto
import com.igor.tv_series.data.models.SeasonDto
import com.igor.tv_series.data.models.SerieDto
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SeriesRepositoryImplTest {

    @Mock
    private lateinit var seriesService: SeriesService

    @Mock
    private lateinit var favoriteSeriesDao: FavoriteSeriesDao

    private lateinit var seriesRepositoryImpl: SeriesRepository

    @Before
    fun setup() {
        seriesRepositoryImpl = SeriesRepositoryImpl(
            seriesService = seriesService,
            favoriteSeriesDao = favoriteSeriesDao
        )
    }

    @Test
    fun fetchSeriesShouldCallCorrectServiceMethod() = runBlockingTest {
        val page = 1

        seriesRepositoryImpl.fetchSeries(page)

        Mockito.verify(seriesService, times(1)).shows(page)
    }

    @Test
    fun fetchSeriesShouldReturnCorrectResult() = runBlockingTest {
        val expected: List<SerieDto> = mock()
        val page = 1
        Mockito.`when`(seriesService.shows(any()))
            .thenReturn(expected)

        val response = seriesRepositoryImpl.fetchSeries(page)

        assertEquals(Result.success(expected), response)
    }

    @Test
    fun searchSeriesShouldCallCorrectServiceMethod() = runBlockingTest {
        val searchTerm = "searchTerm"

        seriesRepositoryImpl.searchSeries(searchTerm)

        Mockito.verify(seriesService, times(1)).searchShows(searchTerm)
    }

    @Test
    fun searchSeriesShouldReturnCorrectResult() = runBlockingTest {
        val expected: List<SearchSerieDto> = mock()
        val searchTerm = "searchTerm"
        Mockito.`when`(seriesService.searchShows(any()))
            .thenReturn(expected)

        val response = seriesRepositoryImpl.searchSeries(searchTerm)

        assertEquals(Result.success(expected), response)
    }

    @Test
    fun fetchEpisodesShouldCallCorrectServiceMethod() = runBlockingTest {
        val seasonId = 1

        seriesRepositoryImpl.fetchEpisodes(seasonId)

        Mockito.verify(seriesService, times(1)).episodesBySeason(seasonId)
    }

    @Test
    fun fetchEpisodesShouldReturnCorrectResult() = runBlockingTest {
        val expected: List<EpisodeDto> = mock()
        val seasonId = 1
        Mockito.`when`(seriesService.episodesBySeason(any()))
            .thenReturn(expected)

        val response = seriesRepositoryImpl.fetchEpisodes(seasonId)

        assertEquals(Result.success(expected), response)
    }

    @Test
    fun fetchSeasonsShouldCallCorrectServiceMethod() = runBlockingTest {
        val serieId = 1

        seriesRepositoryImpl.fetchSeasons(serieId)

        Mockito.verify(seriesService, times(1)).seasons(serieId)
    }

    @Test
    fun fetchSeasonsShouldReturnCorrectResult() = runBlockingTest {
        val expected: List<SeasonDto> = mock()
        val serieId = 1
        Mockito.`when`(seriesService.seasons(any()))
            .thenReturn(expected)

        val response = seriesRepositoryImpl.fetchSeasons(serieId)

        assertEquals(Result.success(expected), response)
    }

    @Test
    fun fetchFavoriteSeriesShouldCallGetAllFromDao() = runBlockingTest {
        seriesRepositoryImpl.fetchFavoriteSeries()

        Mockito.verify(favoriteSeriesDao, times(1)).getAll()
    }

    @Test
    fun fetchFavoriteSeriesShouldReturnCorrectData() = runBlockingTest {
        val favoriteSeries = listOf(mock<FavoriteSeries>())
        val expected = Result.success(favoriteSeries)
        Mockito.`when`(favoriteSeriesDao.getAll())
            .thenReturn(favoriteSeries)

        val response = seriesRepositoryImpl.fetchFavoriteSeries()

        assertEquals(expected, response)
    }

    @Test
    fun insertFavoriteSeriesShouldCallGetAllFromDao() = runBlockingTest {
        val favoriteSeries = listOf(mock<FavoriteSeries>())

        seriesRepositoryImpl.insertFavoriteSeries(favoriteSeries)

        Mockito.verify(favoriteSeriesDao, times(1)).insertAll(favoriteSeries)
    }

    @Test
    fun deleteFavoriteSeriesShouldCallGetAllFromDao() = runBlockingTest {
        val favoriteSeries = listOf(mock<FavoriteSeries>())

        seriesRepositoryImpl.deleteFavoriteSeries(favoriteSeries)

        Mockito.verify(favoriteSeriesDao, times(1)).delete(favoriteSeries)
    }
}