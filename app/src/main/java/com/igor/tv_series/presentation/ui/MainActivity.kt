package com.igor.tv_series.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.igor.tv_series.databinding.ActivityMainBinding
import com.igor.tv_series.presentation.helpers.fadeIn
import com.igor.tv_series.presentation.helpers.fadeOut
import com.igor.tv_series.presentation.helpers.hideSoftKeyboard
import com.igor.tv_series.presentation.ui.serie_details.SerieDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val seriesViewModel: SeriesViewModel by viewModel()

    private val linearLayoutManager = LinearLayoutManager(this)
    private var adapter: SeriesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()

        setupListeners()

        setupObservers()

        seriesViewModel.fetchSeries()
    }

    private fun setupAdapter() {
        binding.seriesRecyclerView.layoutManager = linearLayoutManager
        adapter = SeriesAdapter(this) { serie, _ ->
            startActivity(SerieDetailsActivity.getIntent(this, serie))
        }
        binding.seriesRecyclerView.adapter = adapter
    }

    private fun setupObservers() {
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
            binding.searchSerieLayout.searchEditText.clearFocus()
            binding.searchSerieLayout.searchEditText.text.clear()
        }
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