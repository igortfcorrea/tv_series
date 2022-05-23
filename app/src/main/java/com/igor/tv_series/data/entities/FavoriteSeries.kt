package com.igor.tv_series.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteSeries")
data class FavoriteSeries(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "rating")
    val rating: Float?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?,
    @ColumnInfo(name = "premiered")
    val premiered: String?,
    @ColumnInfo(name = "ended")
    val ended: String?,
    @ColumnInfo(name = "genres")
    val genres: String?,
    @ColumnInfo(name = "summary")
    val summary: String?
)