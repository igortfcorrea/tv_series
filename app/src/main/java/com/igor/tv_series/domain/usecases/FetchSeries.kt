package com.igor.tv_series.domain.usecases

import com.igor.tv_series.domain.State
import com.igor.tv_series.domain.models.SerieModel

interface FetchSeries {
    suspend operator fun invoke(): State<List<SerieModel>>
}