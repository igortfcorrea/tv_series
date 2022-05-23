package com.igor.tv_series.domain.usecases

import com.igor.tv_series.data.models.SearchSerieDto
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
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchSeriesImplTest {
    @Mock
    private lateinit var seriesRepository: SeriesRepository

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var searchSeriesImpl: SearchSeriesImpl

    @Before
    fun setup() {
        searchSeriesImpl = SearchSeriesImpl(
            seriesRepository = seriesRepository,
            ioDispatcher = testCoroutineDispatcher
        )
    }

    @Test
    fun searchSeriesShouldCallCorrectRepositoryMethod() = testCoroutineDispatcher.runBlockingTest {
        val term = "term"

        searchSeriesImpl.invoke(term)

        Mockito.verify(seriesRepository, times(1)).searchSeries(term)
    }

    @Test
    fun searchSeriesShouldReturnSuccess() = testCoroutineDispatcher.runBlockingTest {
        val term = "term"
        val searchSerieDto = SearchSerieDto(1f, mock())
        val listOfSeries = listOf(searchSerieDto)
        val expected = Result.success(listOfSeries)
        Mockito.`when`(seriesRepository.searchSeries(any()))
            .thenReturn(expected)

        val response = searchSeriesImpl.invoke(term)

        Assert.assertTrue(response is Success)
    }

    @Test
    fun searchSeriesShouldReturnCorrectData() = testCoroutineDispatcher.runBlockingTest {
        val term = "term"
        val searchSerieDto = SearchSerieDto(1f, mock())
        val listOfSeries = listOf(searchSerieDto)
        val expected = Result.success(listOfSeries)
        Mockito.`when`(seriesRepository.searchSeries(any()))
            .thenReturn(expected)

        val response = searchSeriesImpl.invoke(term) as Success

        Assert.assertEquals(listOfSeries.map { it.toModel() }, response.result)
    }

    @Test
    fun searchSeriesShouldReturnError() = testCoroutineDispatcher.runBlockingTest {
        val term = "term"
        val throwable = Throwable("something went wrong")
        val expected = Result.failure<List<SearchSerieDto>>(throwable)
        Mockito.`when`(seriesRepository.searchSeries(any()))
            .thenReturn(expected)

        val response = searchSeriesImpl.invoke(term)

        Assert.assertTrue(response is Error)
    }

    @Test
    fun searchSeriesShouldReturnCorrectErrorMessage() = testCoroutineDispatcher.runBlockingTest {
        val term = "term"
        val throwable = Throwable("something went wrong")
        val expected = Result.failure<List<SearchSerieDto>>(throwable)
        Mockito.`when`(seriesRepository.searchSeries(any()))
            .thenReturn(expected)

        val response = searchSeriesImpl.invoke(term) as Error

        Assert.assertEquals("something went wrong", response.message)
    }
}