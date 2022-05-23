package com.igor.tv_series.domain.usecases

import com.igor.tv_series.data.models.EpisodeDto
import com.igor.tv_series.data.models.SeasonDto
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
class FetchSeasonsImplTest {
    @Mock
    private lateinit var seriesRepository: SeriesRepository

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var fetchSeasonsImpl: FetchSeasonsImpl

    @Before
    fun setup() {
        fetchSeasonsImpl = FetchSeasonsImpl(
            seriesRepository = seriesRepository,
            ioDispatcher = testCoroutineDispatcher
        )
    }

    @Test
    fun fetchSeasonsShouldCallCorrectRepositoryMethod() = testCoroutineDispatcher.runBlockingTest {
        val seasonId = 1

        fetchSeasonsImpl.invoke(seasonId)

        Mockito.verify(seriesRepository, times(1)).fetchSeasons(seasonId)
    }

    @Test
    fun fetchSeasonsShouldReturnSuccess() = testCoroutineDispatcher.runBlockingTest {
        val seasonId = 1
        val listOfSeasons = listOf(mock<SeasonDto>())
        val expected = Result.success(listOfSeasons)
        Mockito.`when`(seriesRepository.fetchSeasons(any()))
            .thenReturn(expected)

        val response = fetchSeasonsImpl.invoke(seasonId)

        assertTrue(response is Success)
    }

    @Test
    fun fetchSeasonsShouldReturnCorrectData() = testCoroutineDispatcher.runBlockingTest {
        val seasonId = 1
        val listOfSeasons = listOf(mock<SeasonDto>())
        val expected = Result.success(listOfSeasons)
        Mockito.`when`(seriesRepository.fetchSeasons(any()))
            .thenReturn(expected)

        val response = fetchSeasonsImpl.invoke(seasonId) as Success

        assertEquals(listOfSeasons.map { it.toModel() }, response.result)
    }

    @Test
    fun fetchSeasonsShouldReturnError() = testCoroutineDispatcher.runBlockingTest {
        val seasonId = 1
        val throwable = Throwable("something went wrong")
        val expected = Result.failure<List<SeasonDto>>(throwable)
        Mockito.`when`(seriesRepository.fetchSeasons(any()))
            .thenReturn(expected)

        val response = fetchSeasonsImpl.invoke(seasonId)

        assertTrue(response is Error)
    }

    @Test
    fun fetchSeasonsShouldReturnCorrectErrorMessage() = testCoroutineDispatcher.runBlockingTest {
        val seasonId = 1
        val throwable = Throwable("something went wrong")
        val expected = Result.failure<List<SeasonDto>>(throwable)
        Mockito.`when`(seriesRepository.fetchSeasons(any()))
            .thenReturn(expected)

        val response = fetchSeasonsImpl.invoke(seasonId) as Error

        assertEquals("something went wrong", response.message)
    }
}
