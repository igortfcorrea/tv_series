package com.igor.tv_series.presentation.ui

import androidx.recyclerview.widget.DiffUtil
import com.igor.tv_series.presentation.models.SerieUIModel

class SeriesDiffCallback : DiffUtil.ItemCallback<SerieUIModel>() {
    override fun areItemsTheSame(oldSerie: SerieUIModel, newSerie: SerieUIModel): Boolean {
        return oldSerie.id == newSerie.id
    }

    override fun areContentsTheSame(oldSerie: SerieUIModel, newSerie: SerieUIModel): Boolean {
        return oldSerie == newSerie
    }
}