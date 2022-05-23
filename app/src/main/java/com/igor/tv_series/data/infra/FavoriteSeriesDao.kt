package com.igor.tv_series.data.infra

import androidx.room.*
import com.igor.tv_series.data.entities.FavoriteSeries

@Dao
interface FavoriteSeriesDao {

    @Query("SELECT * FROM FavoriteSeries")
    suspend fun getAll(): List<FavoriteSeries>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(favoriteSeries: List<FavoriteSeries>)

    @Delete
    suspend fun delete(favoriteSeries: List<FavoriteSeries>)

}