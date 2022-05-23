package com.igor.tv_series.presentation.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.igor.tv_series.domain.Success
import com.igor.tv_series.domain.models.SerieModel
import com.igor.tv_series.domain.usecases.FetchSeries
import com.igor.tv_series.domain.usecases.SearchSeries
import com.igor.tv_series.presentation.models.SerieUIModel
import com.igor.tv_series.presentation.models.toUIModel
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
class SeriesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var fetchSeries: FetchSeries

    @Mock
    private lateinit var searchSeries: SearchSeries

    @Mock
    private lateinit var seriesObserver: Observer<List<SerieUIModel>>

    private lateinit var seriesViewModel: SeriesViewModel

    @Before
    fun setup() {
        seriesViewModel = SeriesViewModel(
            fetchSeries = fetchSeries,
            searchSeries = searchSeries
        )

        seriesViewModel.series.observeForever(seriesObserver)
    }

    @After
    fun tearDown() {
        seriesViewModel.series.removeObserver(seriesObserver)
    }

    @Test
    fun fetchSeriesShouldCallTheCorrectUseCase() = runBlockingTest {
        seriesViewModel.fetchSeries()

        Mockito.verify(fetchSeries, times(1)).invoke()
    }

    @Test
    fun fetchSeriesShouldUpdateSeriesLiveData() = runBlockingTest {
        val result = listOf(mock<SerieModel>())
        val expected = Success(result)
        Mockito.`when`(fetchSeries.invoke())
            .thenReturn(expected)

        seriesViewModel.fetchSeries()

        Mockito.verify(seriesObserver).onChanged(result.map { it.toUIModel() })
    }

    @Test
    fun searchShouldCallTheCorrectUseCase() = runBlockingTest {
        val term = "term"

        seriesViewModel.search(term)

        Mockito.verify(searchSeries, times(1)).invoke(term)
    }

    @Test
    fun searchShouldUpdateSeriesLiveData() = runBlockingTest {
        val result = listOf(mock<SerieModel>())
        val expected = Success(result)
        val term = "term"
        Mockito.`when`(searchSeries.invoke(term))
            .thenReturn(expected)

        seriesViewModel.search(term)

        Mockito.verify(seriesObserver).onChanged(result.map { it.toUIModel() })
    }

    @Test
    fun searchShouldCallOnSearcher() = runBlockingTest {
        val term = "term"

        seriesViewModel.search(term)

        Mockito.verify(fetchSeries, times(1)).onSearched()
    }

    @Test
    fun searchShouldFetchSeriesWhenTermIsNull() = runBlockingTest {
        seriesViewModel.search(null)

        Mockito.verify(fetchSeries, times(1)).invoke()
    }

    @Test
    fun searchShouldFetchSeriesWhenTermIsBlank() = runBlockingTest {
        seriesViewModel.search(" ")

        Mockito.verify(fetchSeries, times(1)).invoke()
    }
}