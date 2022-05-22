package com.igor.tv_series.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.igor.tv_series.databinding.ActivityMainBinding
import com.igor.tv_series.helpers.fadeIn
import com.igor.tv_series.helpers.fadeOut
import com.igor.tv_series.helpers.hideSoftKeyboard
import com.igor.tv_series.models.SerieUIModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val linearLayoutManager = LinearLayoutManager(this)
    private var adapter: SeriesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = listOf(
            SerieUIModel(1, 0.997f, "Name test", "asd"),
            SerieUIModel(2, 0.897f, "Name test", "asd"),
            SerieUIModel(3, 0.9797f, "Name test", "asd"),
            SerieUIModel(4, 0.6f, "Name test", "asd"),
            SerieUIModel(5, 0.95f, "Name test", "asd"),
            SerieUIModel(6, 0.123f, "Name test", "asd"),
            SerieUIModel(7, 0.111f, "Name test", "asd"),
            SerieUIModel(8, 0.97f, "Name test", "asd"),
            SerieUIModel(9, 0.987f, "Name test", "asd")
        )
        binding.seriesRecyclerView.layoutManager = linearLayoutManager
        adapter = SeriesAdapter() { series, position ->
            Log.d("click -->", "item position: $position")
        }
        binding.seriesRecyclerView.adapter = adapter
        adapter?.submitList(list)

        setupListeners()
    }

    private fun setupListeners() {
        binding.searchSerieLayout.searchEditText.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.searchSerieLayout.searchClearImageView.fadeIn()
            } else {
                binding.searchSerieLayout.searchClearImageView.fadeOut()
                hideSoftKeyboard(view)
            }
        }

        binding.searchSerieLayout.searchClearImageView.setOnClickListener {
            binding.searchSerieLayout.searchEditText.clearFocus()
            binding.searchSerieLayout.searchEditText.text.clear()
        }
    }
}