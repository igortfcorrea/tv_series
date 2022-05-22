package com.igor.tv_series.presentation.ui.serie_details

import androidx.recyclerview.widget.DiffUtil
import com.igor.tv_series.presentation.models.EpisodeUIModel

class EpisodesDiffCallback : DiffUtil.ItemCallback<EpisodeUIModel>() {
    override fun areItemsTheSame(oldEpisode: EpisodeUIModel, newEpisode: EpisodeUIModel): Boolean {
        return oldEpisode.season == newEpisode.season && oldEpisode.number == newEpisode.number
    }

    override fun areContentsTheSame(oldEpisode: EpisodeUIModel, newEpisode: EpisodeUIModel): Boolean {
        return oldEpisode == newEpisode
    }
}