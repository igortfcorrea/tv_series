package com.igor.tv_series.presentation.di

import com.igor.tv_series.presentation.ui.SeriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        SeriesViewModel(
            fetchSeries = get(),
            searchSeries = get()
        )
    }
}