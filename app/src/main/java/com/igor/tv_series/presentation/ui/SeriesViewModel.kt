package com.igor.tv_series.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igor.tv_series.domain.Success
import com.igor.tv_series.domain.usecases.FetchSeries
import com.igor.tv_series.domain.usecases.SearchSeries
import com.igor.tv_series.presentation.models.SerieUIModel
import com.igor.tv_series.presentation.models.toUIModel
import kotlinx.coroutines.launch

class SeriesViewModel(
    private val fetchSeries: FetchSeries,
    private val searchSeries: SearchSeries
) : ViewModel() {

    private val mutableSeries = MutableLiveData<List<SerieUIModel>>()
    val series: LiveData<List<SerieUIModel>>
        get() = mutableSeries

    fun fetchSeries() {
        viewModelScope.launch {
            fetchSeries.invoke().also { series ->
                when (series) {
                    is Success -> {
                        mutableSeries.value = series.result.map { it.toUIModel() }
                    }
                }
            }
        }
    }

    fun search(term: String?) {
        if (!term.isNullOrBlank()) {
            viewModelScope.launch {
                fetchSeries.onSearched()
                searchSeries.invoke(term.trim()).also { series ->
                    when (series) {
                        is Success -> {
                            mutableSeries.value = series.result.map { it.toUIModel() }
                        }
                    }
                }
            }
        }
    }
}