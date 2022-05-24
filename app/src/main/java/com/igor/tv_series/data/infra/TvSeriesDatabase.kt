package com.igor.tv_series.data.infra

import androidx.room.Database
import androidx.room.RoomDatabase
import com.igor.tv_series.data.entities.FavoriteSeries
import com.igor.tv_series.data.infra.TvSeriesDatabase.Companion.VERSION

@Database(
    entities = [FavoriteSeries::class],
    version = VERSION
)
internal abstract class TvSeriesDatabase : RoomDatabase() {
    abstract fun favoriteSeriesDao(): FavoriteSeriesDao

    companion object {
        const val VERSION = 48
        const val DATABASE_FILE_NAME = "tv_series_database.db"
    }
}
