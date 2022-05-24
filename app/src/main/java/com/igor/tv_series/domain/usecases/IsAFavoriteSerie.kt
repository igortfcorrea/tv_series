package com.igor.tv_series.domain.usecases

interface IsAFavoriteSerie {
    suspend operator fun invoke(id: Int): Boolean
}