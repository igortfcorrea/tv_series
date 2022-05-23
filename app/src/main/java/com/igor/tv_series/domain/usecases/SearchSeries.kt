package com.igor.tv_series.domain.usecases

import com.igor.tv_series.domain.State
import com.igor.tv_series.domain.models.SerieModel

interface SearchSeries {
    suspend operator fun invoke(term: String): State<List<SerieModel>>
}