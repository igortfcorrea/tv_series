package com.igor.tv_series.presentation.di

import com.igor.tv_series.presentation.ui.SeriesViewModel
import com.igor.tv_series.presentation.ui.serie_details.SerieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        SeriesViewModel(
            fetchSeries = get(),
            searchSeries = get(),
        )
    }

    viewModel {
        SerieDetailsViewModel(
            fetchEpisodes = get(),
            fetchSeasons = get(),
            insertFavoriteSeries = get(),
            deleteFavoriteSeries = get(),
            isAFavoriteSerie = get()
        )
    }
}