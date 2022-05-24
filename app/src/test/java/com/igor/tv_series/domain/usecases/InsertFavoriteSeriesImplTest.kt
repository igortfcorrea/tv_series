package com.igor.tv_series.domain.usecases

import com.igor.tv_series.data.repositories.SeriesRepository
import com.igor.tv_series.domain.models.FavoriteSerieModel
import com.igor.tv_series.domain.models.toEntity
import com.nhaarman.mockitokotlin2.times
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
class InsertFavoriteSeriesImplTest {

    @Mock
    private lateinit var seriesRepository: SeriesRepository

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var insertFavoriteSeries: InsertFavoriteSeries

    @Before
    fun setup() {
        insertFavoriteSeries = InsertFavoriteSeriesImpl(
            seriesRepository = seriesRepository,
            ioDispatcher = testCoroutineDispatcher
        )
    }

    @Test
    fun insertFavoriteSeriesShouldCallCorrectRepositoryMethod() = testCoroutineDispatcher.runBlockingTest {
        val favoriteSeries = listOf(
            FavoriteSerieModel(1, null, null, null, null, null, null, null)
        )

        insertFavoriteSeries.invoke(favoriteSeries)

        Mockito.verify(seriesRepository, times(1)).insertFavoriteSeries(favoriteSeries.map { it.toEntity() })
    }
}