package com.igor.tv_series.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igor.tv_series.domain.Success
import com.igor.tv_series.domain.usecases.*
import com.igor.tv_series.presentation.models.SerieUIModel
import com.igor.tv_series.presentation.models.toModel
import com.igor.tv_series.presentation.models.toUIModel
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList

class SeriesViewModel(
    private val fetchSeries: FetchSeries,
    private val searchSeries: SearchSeries,
    private val fetchFavoriteSeries: FetchFavoriteSeries
) : ViewModel() {

    private val _series = MutableLiveData<List<SerieUIModel>>()
    val series: LiveData<List<SerieUIModel>>
        get() = _series

    private var isFetchingSeries = false

    fun fetchSeries(refreshList: Boolean = false) {
        viewModelScope.launch {
            isFetchingSeries = true
            fetchSeries.invoke(refreshList).also { series ->
                when (series) {
                    is Success -> {
                        if (refreshList)
                            _series.value = series.result.map { it.toUIModel() }
                        else {
                            val newList = _series.value?.toMutableList() ?: mutableListOf()
                            newList.addAll(series.result.map { it.toUIModel() })
                            _series.value = newList
                        }
                    }
                }
                isFetchingSeries = false
            }
        }
    }

    fun search(term: String?) {
        if (!term.isNullOrBlank()) {
            viewModelScope.launch {
                isFetchingSeries = true
                fetchSeries.onSearched()
                searchSeries.invoke(term.trim()).also { series ->
                    when (series) {
                        is Success -> {
                            _series.value = series.result.map { it.toUIModel() }
                        }
                    }
                    isFetchingSeries = false
                }
            }
        } else {
            fetchSeries(true)
        }
    }

    fun isFetchingSeries(): Boolean {
        return isFetchingSeries
    }

    fun fetchFavorites() {
        viewModelScope.launch {
            isFetchingSeries = true
            fetchFavoriteSeries.invoke().also { series ->
                when (series) {
                    is Success -> {
                        _series.value = series.result.map { it.toUIModel() }
                    }
                }
                isFetchingSeries = false
            }
        }
    }
}