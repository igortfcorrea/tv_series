package com.igor.tv_series

import android.app.Application
import com.igor.tv_series.data.di.dataModule
import com.igor.tv_series.domain.di.domainModule
import com.igor.tv_series.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class TvSeriesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TvSeriesApplication)
            modules(dataModule + domainModule + presentationModule)
        }
    }
}