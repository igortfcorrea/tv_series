package com.igor.tv_series.domain.di

import com.igor.tv_series.domain.usecases.FetchEpisodes
import com.igor.tv_series.domain.usecases.FetchEpisodesImpl
import com.igor.tv_series.domain.usecases.FetchSeries
import com.igor.tv_series.domain.usecases.FetchSeriesImpl
import org.koin.dsl.module

val domainModule = module {
    single<FetchSeries> {
        FetchSeriesImpl(
            seriesRepository = get()
        )
    }

    single<FetchEpisodes> {
        FetchEpisodesImpl(
            seriesRepository = get()
        )
    }
}