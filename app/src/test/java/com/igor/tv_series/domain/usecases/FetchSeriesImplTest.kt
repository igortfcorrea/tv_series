package com.igor.tv_series.domain.usecases

import com.igor.tv_series.data.models.SerieDto
import com.igor.tv_series.data.repositories.SeriesRepository
import com.igor.tv_series.domain.Error
import com.igor.tv_series.domain.Success
import com.igor.tv_series.domain.models.toModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FetchSeriesImplTest {
    @Mock
    private lateinit var seriesRepository: SeriesRepository

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var fetchSeriesImpl: FetchSeriesImpl

    @Before
    fun setup() {
        fetchSeriesImpl = FetchSeriesImpl(
            seriesRepository = seriesRepository,
            ioDispatcher = testCoroutineDispatcher
        )
    }

    @Test
    fun fetchSeriesShouldCallCorrectRepositoryMethod() = testCoroutineDispatcher.runBlockingTest {
        fetchSeriesImpl.invoke()

        Mockito.verify(seriesRepository, times(1)).fetchSeries(0)
    }

    @Test
    fun fetchSeriesShouldReturnSuccess() = testCoroutineDispatcher.runBlockingTest {
        val listOfSeries = listOf(mock<SerieDto>())
        val expected = Result.success(listOfSeries)
        Mockito.`when`(seriesRepository.fetchSeries(any()))
            .thenReturn(expected)

        val response = fetchSeriesImpl.invoke()

        assertTrue(response is Success)
    }

    @Test
    fun fetchSeriesShouldReturnCorrectData() = testCoroutineDispatcher.runBlockingTest {
        val listOfSeries = listOf(mock<SerieDto>())
        val expected = Result.success(listOfSeries)
        Mockito.`when`(seriesRepository.fetchSeries(any()))
            .thenReturn(expected)

        val response = fetchSeriesImpl.invoke() as Success

        assertEquals(listOfSeries.map { it.toModel(favoriteSeries) }, response.result)
    }

    @Test
    fun fetchSeriesShouldReturnError() = testCoroutineDispatcher.runBlockingTest {
        val throwable = Throwable("something went wrong")
        val expected = Result.failure<List<SerieDto>>(throwable)
        Mockito.`when`(seriesRepository.fetchSeries(any()))
            .thenReturn(expected)

        val response = fetchSeriesImpl.invoke()

        assertTrue(response is Error)
    }

    @Test
    fun fetchSeriesShouldReturnCorrectErrorMessage() = testCoroutineDispatcher.runBlockingTest {
        val throwable = Throwable("something went wrong")
        val expected = Result.failure<List<SerieDto>>(throwable)
        Mockito.`when`(seriesRepository.fetchSeries(any()))
            .thenReturn(expected)

        val response = fetchSeriesImpl.invoke() as Error

        assertEquals("something went wrong", response.message)
    }

    @Test
    fun currentPageShouldIncreaseAfterASuccessCall() = testCoroutineDispatcher.runBlockingTest {
        val listOfSeries = listOf(mock<SerieDto>())
        val expected = Result.success(listOfSeries)
        Mockito.`when`(seriesRepository.fetchSeries(any()))
            .thenReturn(expected)

        fetchSeriesImpl.invoke()

        Mockito.verify(seriesRepository, times(1)).fetchSeries(0)

        fetchSeriesImpl.invoke()

        Mockito.verify(seriesRepository, times(1)).fetchSeries(1)
    }
}