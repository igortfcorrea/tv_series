package com.igor.tv_series.domain.di

import com.igor.tv_series.domain.usecases.*
import com.igor.tv_series.domain.usecases.FetchEpisodesImpl
import com.igor.tv_series.domain.usecases.FetchSeriesImpl
import org.koin.dsl.module

val domainModule = module {
    single<FetchSeries> {
        FetchSeriesImpl(
            seriesRepository = get()
        )
    }

    single<SearchSeries> {
        SearchSeriesImpl(
            seriesRepository = get()
        )
    }

    single<FetchEpisodes> {
        FetchEpisodesImpl(
            seriesRepository = get()
        )
    }

    single<FetchSeasons> {
        FetchSeasonsImpl(
            seriesRepository = get()
        )
    }

    single<FetchFavoriteSeries> {
        FetchFavoriteSeriesImpl(
            seriesRepository = get()
        )
    }

    single<DeleteFavoriteSeries> {
        DeleteFavoriteSeriesImpl(
            seriesRepository = get()
        )
    }

    single<InsertFavoriteSeries> {
        InsertFavoriteSeriesImpl(
            seriesRepository = get()
        )
    }
}