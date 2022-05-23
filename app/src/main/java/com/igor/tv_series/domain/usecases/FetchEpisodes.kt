package com.igor.tv_series.domain.usecases

import com.igor.tv_series.domain.State
import com.igor.tv_series.domain.models.EpisodeModel

interface FetchEpisodes {
    suspend operator fun invoke(seasonId: Int): State<List<EpisodeModel>>
}