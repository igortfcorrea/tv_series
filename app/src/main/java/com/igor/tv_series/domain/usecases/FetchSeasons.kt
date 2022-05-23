package com.igor.tv_series.domain.usecases

import com.igor.tv_series.domain.State
import com.igor.tv_series.domain.models.SeasonModel

interface FetchSeasons {
    suspend operator fun invoke(serieId: Int): State<List<SeasonModel>>
}