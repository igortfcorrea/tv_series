package com.igor.tv_series.domain.usecases

import com.igor.tv_series.data.repositories.SeriesRepository
import com.igor.tv_series.domain.models.FavoriteSerieModel
import com.igor.tv_series.domain.models.toEntity
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
class DeleteFavoriteSeriesImplTest {

    @Mock
    private lateinit var seriesRepository: SeriesRepository

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var deleteFavoriteSeries: DeleteFavoriteSeries

    @Before
    fun setup() {
        deleteFavoriteSeries = DeleteFavoriteSeriesImpl(
            seriesRepository = seriesRepository,
            ioDispatcher = testCoroutineDispatcher
        )
    }

    @Test
    fun deleteFavoriteSeriesShouldCallCorrectRepositoryMethod() = testCoroutineDispatcher.runBlockingTest {
        val favoriteSeries = listOf(
            FavoriteSerieModel(1, null, null, null, null, null, null, null)
        )

        deleteFavoriteSeries.invoke(favoriteSeries)

        Mockito.verify(seriesRepository, times(1)).deleteFavoriteSeries(favoriteSeries.map { it.toEntity() })
    }
}