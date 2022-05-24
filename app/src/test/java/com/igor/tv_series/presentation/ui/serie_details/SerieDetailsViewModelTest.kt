package com.igor.tv_series.presentation.ui.serie_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.igor.tv_series.domain.Success
import com.igor.tv_series.domain.models.EpisodeModel
import com.igor.tv_series.domain.models.SeasonModel
import com.igor.tv_series.domain.usecases.*
import com.igor.tv_series.presentation.models.EpisodeUIModel
import com.igor.tv_series.presentation.models.SeasonUIModel
import com.igor.tv_series.presentation.models.toUIModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SerieDetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var fetchEpisodes: FetchEpisodes

    @Mock
    private lateinit var fetchSeasons: FetchSeasons

    @Mock
    private lateinit var insertFavoriteSeries: InsertFavoriteSeries

    @Mock
    private lateinit var deleteFavoriteSeries: DeleteFavoriteSeries

    @Mock
    private lateinit var isAFavoriteSerie: IsAFavoriteSerie

    @Mock
    private lateinit var episodesObserver: Observer<List<EpisodeUIModel>>

    @Mock
    private lateinit var seasonsObserver: Observer<List<SeasonUIModel>>

    private lateinit var seriesDetailsViewModel: SerieDetailsViewModel

    @Before
    fun setup() {
        seriesDetailsViewModel = SerieDetailsViewModel(
            fetchEpisodes = fetchEpisodes,
            fetchSeasons = fetchSeasons,
            insertFavoriteSeries = insertFavoriteSeries,
            deleteFavoriteSeries = deleteFavoriteSeries,
            isAFavoriteSerie = isAFavoriteSerie
        )

        seriesDetailsViewModel.episodes.observeForever(episodesObserver)
        seriesDetailsViewModel.seasons.observeForever(seasonsObserver)
    }

    @After
    fun tearDown() {
        seriesDetailsViewModel.episodes.removeObserver(episodesObserver)
        seriesDetailsViewModel.seasons.removeObserver(seasonsObserver)
    }

    @Test
    fun fetchEpisodesShouldCallCorrectUseCase() = runBlockingTest {
        val seasonId = 1

        seriesDetailsViewModel.fetchEpisodes(seasonId)

        Mockito.verify(fetchEpisodes, times(1)).invoke(seasonId)
    }

    @Test
    fun fetchEpisodesShouldUpdateEpisodesLiveData() = runBlockingTest {
        val seasonId = 1
        val result = listOf(mock<EpisodeModel>())
        val expected = Success(result)
        Mockito.`when`(fetchEpisodes.invoke(any()))
            .thenReturn(expected)

        seriesDetailsViewModel.fetchEpisodes(seasonId)

        Mockito.verify(episodesObserver).onChanged(result.map { it.toUIModel() })
    }

    @Test
    fun fetchSeasonsShouldCallCorrectUseCase() = runBlockingTest {
        val serieId = 1

        seriesDetailsViewModel.fetchSeasons(serieId)

        Mockito.verify(fetchSeasons, times(1)).invoke(serieId)
    }

    @Test
    fun fetchSeasonsShouldUpdateEpisodesLiveData() = runBlockingTest {
        val serieId = 1
        val result = listOf(mock<SeasonModel>())
        val expected = Success(result)
        Mockito.`when`(fetchSeasons.invoke(any()))
            .thenReturn(expected)

        seriesDetailsViewModel.fetchSeasons(serieId)

        Mockito.verify(seasonsObserver).onChanged(result.map { it.toUIModel() })
    }

    @Test
    fun setSeasonShouldFetchCorrectEpisodes() = runBlockingTest {
        val serieId = 1
        val result = listOf(SeasonModel(1, 1), SeasonModel(2, 2))
        val expected = Success(result)
        Mockito.`when`(fetchSeasons.invoke(any()))
            .thenReturn(expected)

        seriesDetailsViewModel.fetchSeasons(serieId)

        seriesDetailsViewModel.setSeason(1)

        Mockito.verify(fetchEpisodes, times(1)).invoke(2)
    }

    @Test
    fun setSeasonShouldCallNothingWhenPositionIsBiggerThanSeasonsSize() {
        seriesDetailsViewModel.setSeason(10)

        Mockito.verifyNoInteractions(fetchEpisodes)
    }
}