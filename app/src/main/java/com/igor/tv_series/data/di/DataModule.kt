package com.igor.tv_series.data.di

import com.igor.tv_series.data.infra.http.SeriesService
import com.igor.tv_series.data.infra.local.TvSeriesDatabase
import com.igor.tv_series.data.infra.local.TvSeriesDatabaseManager
import com.igor.tv_series.data.infra.http.RetrofitInstance
import com.igor.tv_series.data.repositories.SeriesRepository
import com.igor.tv_series.data.repositories.SeriesRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single { Retrofit.Builder()
        .baseUrl("https://api.tvmaze.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(RetrofitInstance().client)
        .build()
        .create(SeriesService::class.java) as SeriesService
    }

    single {
        TvSeriesDatabaseManager.build(androidContext())
    }

    single { get<TvSeriesDatabase>().favoriteSeriesDao() }

    single<SeriesRepository> {
        SeriesRepositoryImpl(
            seriesService = get(),
            favoriteSeriesDao = get()
        )
    }
}