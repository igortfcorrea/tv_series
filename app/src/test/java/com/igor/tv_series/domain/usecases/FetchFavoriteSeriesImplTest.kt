package com.igor.tv_series.domain.usecases

import com.igor.tv_series.data.entities.FavoriteSeries
import com.igor.tv_series.data.repositories.SeriesRepository
import com.igor.tv_series.domain.Error
import com.igor.tv_series.domain.Success
import com.igor.tv_series.domain.models.toModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FetchFavoriteSeriesImplTest {

    @Mock
    private lateinit var seriesRepository: SeriesRepository

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var fetchFavoriteSeries: FetchFavoriteSeries

    @Before
    fun setup() {
        fetchFavoriteSeries = FetchFavoriteSeriesImpl(
            seriesRepository = seriesRepository,
            ioDispatcher = testCoroutineDispatcher
        )
    }

    @Test
    fun fetchFavoriteSeriesShouldCallCorrectRepositoryMethod() = testCoroutineDispatcher.runBlockingTest {
        fetchFavoriteSeries.invoke()

        Mockito.verify(seriesRepository, times(1)).fetchFavoriteSeries()
    }

    @Test
    fun fetchFavoriteSeriesShouldReturnSuccess() = testCoroutineDispatcher.runBlockingTest {
        val listOfSeries = listOf(mock<FavoriteSeries>())
        val expected = Result.success(listOfSeries)
        Mockito.`when`(seriesRepository.fetchFavoriteSeries())
            .thenReturn(expected)

        val response = fetchFavoriteSeries.invoke()

        Assert.assertTrue(response is Success)
    }

    @Test
    fun fetchFavoriteSeriesShouldReturnCorrectData() = testCoroutineDispatcher.runBlockingTest {
        val listOfSeries = listOf(mock<FavoriteSeries>())
        val expected = Result.success(listOfSeries)
        Mockito.`when`(seriesRepository.fetchFavoriteSeries())
            .thenReturn(expected)

        val response = fetchFavoriteSeries.invoke() as Success

        Assert.assertEquals(listOfSeries.map { it.toModel() }, response.result)
    }

    @Test
    fun fetchFavoriteSeriesShouldReturnError() = testCoroutineDispatcher.runBlockingTest {
        val throwable = Throwable("something went wrong")
        val expected = Result.failure<List<FavoriteSeries>>(throwable)
        Mockito.`when`(seriesRepository.fetchFavoriteSeries())
            .thenReturn(expected)

        val response = fetchFavoriteSeries.invoke()

        Assert.assertTrue(response is Error)
    }

    @Test
    fun fetchFavoriteSeriesShouldReturnCorrectErrorMessage() = testCoroutineDispatcher.runBlockingTest {
        val throwable = Throwable("something went wrong")
        val expected = Result.failure<List<FavoriteSeries>>(throwable)
        Mockito.`when`(seriesRepository.fetchFavoriteSeries())
            .thenReturn(expected)

        val response = fetchFavoriteSeries.invoke() as Error

        Assert.assertEquals("something went wrong", response.message)
    }
}