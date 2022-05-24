package com.igor.tv_series.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.igor.tv_series.databinding.ActivitySeriesBinding
import com.igor.tv_series.presentation.helpers.EditTextWatcher
import com.igor.tv_series.presentation.helpers.fadeIn
import com.igor.tv_series.presentation.helpers.fadeOut
import com.igor.tv_series.presentation.helpers.hideSoftKeyboard
import com.igor.tv_series.presentation.ui.serie_details.SerieDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeriesBinding

    private val seriesViewModel: SeriesViewModel by viewModel()

    private val linearLayoutManager = LinearLayoutManager(this)
    private var adapter: SeriesAdapter? = null

    private val editTextWatcher: TextWatcher =
        EditTextWatcher { term ->
            seriesViewModel.search(term)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()

        setupListeners()

        setupObservers()

        seriesViewModel.fetchSeries()
    }

    private fun setupAdapter() {
        binding.seriesRecyclerView.layoutManager = linearLayoutManager
        adapter = SeriesAdapter(
            context = this,
            onItemClicked = { serie, _ ->
                startActivity(SerieDetailsActivity.getIntent(this, serie))
            })
        binding.seriesRecyclerView.adapter = adapter
    }

    private fun setupObservers() {
        binding.searchSerieLayout.searchEditText.addTextChangedListener(editTextWatcher)

        seriesViewModel.series.observe(this) { series ->
            adapter?.submitList(series)
        }
    }

    private fun setupListeners() {
        binding.searchSerieLayout.searchEditText.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                hideClearSearch()
            } else {
                showClearSearch()
                hideSoftKeyboard(view)
            }
        }

        binding.searchSerieLayout.clearSearchImageView.setOnClickListener {
            clearSearch()
        }

        binding.seriesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) &&
                    !seriesViewModel.isFetchingSeries() &&
                    !binding.onlyFavoritesCheckBox.isChecked
                ) {
                    seriesViewModel.fetchSeries()
                }
            }
        })

        binding.onlyFavoritesCheckBox.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                binding.searchSerieLayout.searchBarContainer.visibility = GONE
                seriesViewModel.fetchFavorites()
            } else {
                binding.searchSerieLayout.searchBarContainer.visibility = VISIBLE
                seriesViewModel.fetchSeries(refreshList = true)
                binding.searchSerieLayout.searchEditText.removeTextChangedListener(editTextWatcher)
                clearSearch()
                binding.searchSerieLayout.searchEditText.addTextChangedListener(editTextWatcher)
            }
        }
    }

    private fun clearSearch() {
        binding.searchSerieLayout.searchEditText.clearFocus()
        binding.searchSerieLayout.searchEditText.text.clear()
    }

    private fun showClearSearch() {
        binding.searchSerieLayout.clearSearchImageView.fadeOut()
        binding.searchSerieLayout.clearSearchImageView.isClickable = false
    }

    private fun hideClearSearch() {
        binding.searchSerieLayout.clearSearchImageView.fadeIn()
        binding.searchSerieLayout.clearSearchImageView.isClickable = true
    }
}