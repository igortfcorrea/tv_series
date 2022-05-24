package com.igor.tv_series.presentation.ui.serie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igor.tv_series.domain.Success
import com.igor.tv_series.domain.usecases.*
import com.igor.tv_series.presentation.models.*
import kotlinx.coroutines.launch

class SerieDetailsViewModel(
    private val fetchEpisodes: FetchEpisodes,
    private val fetchSeasons: FetchSeasons,
    private val favoriteSeries: InsertFavoriteSeries,
    private val deleteFavoriteSeries: DeleteFavoriteSeries
) : ViewModel() {

    private val _episodes = MutableLiveData<List<EpisodeUIModel>>()
    val episodes: LiveData<List<EpisodeUIModel>>
        get() = _episodes

    private val _seasons = MutableLiveData<List<SeasonUIModel>>()
    val seasons: LiveData<List<SeasonUIModel>>
        get() = _seasons

    fun fetchEpisodes(seasonId: Int) {
        viewModelScope.launch {
            fetchEpisodes.invoke(seasonId).also { episodes ->
                when (episodes) {
                    is Success -> {
                        _episodes.value = episodes.result.map { it.toUIModel() }
                    }
                }
            }
        }
    }

    fun fetchSeasons(serieId: Int) {
        viewModelScope.launch {
            fetchSeasons.invoke(serieId).also { seasons ->
                when (seasons) {
                    is Success -> {
                        _seasons.value = seasons.result.map { it.toUIModel() }
                    }
                }
            }
        }
    }

    fun setSeason(position: Int) {
        seasons.value?.size?.let { size ->
            if (position < size) {
                val seasonId = _seasons.value?.get(position)?.id ?: 1
                fetchEpisodes(seasonId)
            }
        }
    }

    fun favoriteSerie(serie: SerieUIModel) {
        viewModelScope.launch {
            favoriteSeries.invoke(listOf(serie.toModel()))
        }
    }

    fun deleteSerie(serie: SerieUIModel) {
        viewModelScope.launch {
            deleteFavoriteSeries.invoke(listOf(serie.toModel()))
        }
    }
}