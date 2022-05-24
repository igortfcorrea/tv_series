package com.igor.tv_series.domain.usecases

import com.igor.tv_series.data.repositories.SeriesRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import junit.framework.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class IsAFavoriteSerieImplTest {

    @Mock
    private lateinit var seriesRepository: SeriesRepository

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var isAFavoriteSeries: IsAFavoriteSerie

    @Before
    fun setup() {
        isAFavoriteSeries = IsAFavoriteSerieImpl(
            seriesRepository = seriesRepository,
            ioDispatcher = testCoroutineDispatcher
        )
    }

    @Test
    fun isAFavoriteSerieShouldCallCorrectRepositoryMethod() = testCoroutineDispatcher.runBlockingTest {
        val id = 1

        isAFavoriteSeries.invoke(id)

        Mockito.verify(seriesRepository, times(1)).isAFavoriteSerie(id)
    }

    @Test
    fun isAFavoriteSerieShouldReturnCorrectValueTrue() = testCoroutineDispatcher.runBlockingTest {
        val id = 1
        Mockito.`when`(seriesRepository.isAFavoriteSerie(any()))
            .thenReturn(true)

        val result = isAFavoriteSeries.invoke(id)

        Assert.assertEquals(true, result)
    }

    @Test
    fun isAFavoriteSerieShouldReturnCorrectValueFalse() = testCoroutineDispatcher.runBlockingTest {
        val id = 1
        Mockito.`when`(seriesRepository.isAFavoriteSerie(any()))
            .thenReturn(false)

        val result = isAFavoriteSeries.invoke(id)

        Assert.assertEquals(false, result)
    }
}