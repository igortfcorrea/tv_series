package com.igor.tv_series.domain.usecases

import com.igor.tv_series.data.models.EpisodeDto
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
class FetchEpisodesImplTest {

    @Mock
    private lateinit var seriesRepository: SeriesRepository

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var fetchEpisodesImpl: FetchEpisodes

    @Before
    fun setup() {
        fetchEpisodesImpl = FetchEpisodesImpl(
            seriesRepository = seriesRepository,
            ioDispatcher = testCoroutineDispatcher
        )
    }

    @Test
    fun fetchEpisodesShouldCallCorrectRepositoryMethod() = testCoroutineDispatcher.runBlockingTest {
        val seasonId = 1

        fetchEpisodesImpl.invoke(seasonId)

        Mockito.verify(seriesRepository, times(1)).fetchEpisodes(seasonId)
    }

    @Test
    fun fetchEpisodesShouldReturnSuccess() = testCoroutineDispatcher.runBlockingTest {
        val seasonId = 1
        val listOfEpisodes = listOf(mock<EpisodeDto>())
        val expected = Result.success(listOfEpisodes)
        Mockito.`when`(seriesRepository.fetchEpisodes(any()))
            .thenReturn(expected)

        val response = fetchEpisodesImpl.invoke(seasonId)

        Assert.assertTrue(response is Success)
    }

    @Test
    fun fetchEpisodesShouldReturnCorrectData() = testCoroutineDispatcher.runBlockingTest {
        val seasonId = 1
        val listOfEpisodes = listOf(mock<EpisodeDto>())
        val expected = Result.success(listOfEpisodes)
        Mockito.`when`(seriesRepository.fetchEpisodes(any()))
            .thenReturn(expected)

        val response = fetchEpisodesImpl.invoke(seasonId) as Success

        Assert.assertEquals(listOfEpisodes.map { it.toModel() }, response.result)
    }

    @Test
    fun fetchEpisodesShouldReturnError() = testCoroutineDispatcher.runBlockingTest {
        val seasonId = 1
        val throwable = Throwable("something went wrong")
        val expected = Result.failure<List<EpisodeDto>>(throwable)
        Mockito.`when`(seriesRepository.fetchEpisodes(any()))
            .thenReturn(expected)

        val response = fetchEpisodesImpl.invoke(seasonId)

        Assert.assertTrue(response is Error)
    }

    @Test
    fun fetchEpisodesShouldReturnCorrectErrorMessage() = testCoroutineDispatcher.runBlockingTest {
        val seasonId = 1
        val throwable = Throwable("something went wrong")
        val expected = Result.failure<List<EpisodeDto>>(throwable)
        Mockito.`when`(seriesRepository.fetchEpisodes(any()))
            .thenReturn(expected)

        val response = fetchEpisodesImpl.invoke(seasonId) as Error

        Assert.assertEquals("something went wrong", response.message)
    }
}