package com.igor.tv_series.data.infra.local

import android.content.Context
import androidx.room.Room
import com.igor.tv_series.data.infra.local.TvSeriesDatabase.Companion.DATABASE_FILE_NAME

internal object TvSeriesDatabaseManager {
    fun build(context: Context): TvSeriesDatabase {
        return Room.databaseBuilder(
            context,
            TvSeriesDatabase::class.java,
            DATABASE_FILE_NAME
        ).build()
    }
}