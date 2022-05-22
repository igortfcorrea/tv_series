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
import com.igor.tv_series.ui.serie_details.SerieDetailsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val linearLayoutManager = LinearLayoutManager(this)
    private var adapter: SeriesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = listOf(
            SerieUIModel(1, 0.997f, "Ozark", "https://static.tvmaze.com/uploads/images/medium_portrait/31/78286.jpg", "2013-04-05", "2013-04-05", listOf("Drama", "Thriller", "Science-Fiction"), "<p><b>Under the Dome</b>…en it will go away.</p>"),
            SerieUIModel(2, 0.897f, "Ozark", "https://static.tvmaze.com/uploads/images/medium_portrait/31/78286.jpg","2013-04-05", "2013-04-05", listOf("Drama", "Thriller", "Science-Fiction"), "<p><b>Under the Dome</b>…en it will go away.</p>"),
            SerieUIModel(3, 0.9797f, "Ozark", "https://static.tvmaze.com/uploads/images/medium_portrait/31/78286.jpg", "2013-04-05", "2013-04-05", listOf("Drama", "Thriller", "Science-Fiction"), "<p><b>Under the Dome</b>…en it will go away.</p>"),
            SerieUIModel(4, 0.6f, "Ozark", "https://static.tvmaze.com/uploads/images/medium_portrait/31/78286.jpg", "2013-04-05", "2013-04-05", listOf("Drama", "Thriller", "Science-Fiction"), "<p><b>Under the Dome</b>…en it will go away.</p>"),
            SerieUIModel(5, 0.95f, "Ozark", "https://static.tvmaze.com/uploads/images/medium_portrait/31/78286.jpg", "2013-04-05", "2013-04-05", listOf("Drama", "Thriller", "Science-Fiction"), "<p><b>Under the Dome</b>…en it will go away.</p>"),
            SerieUIModel(6, 0.123f, "Ozark", "https://static.tvmaze.com/uploads/images/medium_portrait/31/78286.jpg", "2013-04-05", "2013-04-05", listOf("Drama", "Thriller", "Science-Fiction"), "<p><b>Under the Dome</b>…en it will go away.</p>"),
            SerieUIModel(7, 0.111f, "Ozark", "https://static.tvmaze.com/uploads/images/medium_portrait/31/78286.jpg", "2013-04-05", "2013-04-05", listOf("Drama", "Thriller", "Science-Fiction"), "<p><b>Under the Dome</b>…en it will go away.</p>"),
            SerieUIModel(8, 0.97f, "Ozark", "https://static.tvmaze.com/uploads/images/medium_portrait/31/78286.jpg", "2013-04-05", "2013-04-05", listOf("Drama", "Thriller", "Science-Fiction"), "<p><b>Under the Dome</b>…en it will go away.</p>"),
            SerieUIModel(9, 0.987f, "Ozark", "https://static.tvmaze.com/uploads/images/medium_portrait/31/78286.jpg", "2013-04-05", "2013-04-05", listOf("Drama", "Thriller", "Science-Fiction"), "<p><b>Under the Dome</b>…en it will go away.</p>")
        )
        binding.seriesRecyclerView.layoutManager = linearLayoutManager
        adapter = SeriesAdapter(this) { serie, _ ->
            startActivity(SerieDetailsActivity.getIntent(this, serie))
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