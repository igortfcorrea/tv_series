package com.igor.tv_series.ui.serie_details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.igor.tv_series.R
import com.igor.tv_series.databinding.ActivitySerieDetailsBinding
import com.igor.tv_series.helpers.loadImage
import com.igor.tv_series.models.SerieUIModel

class SerieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySerieDetailsBinding
    private val serie by lazy { intent?.extras?.getParcelable<SerieUIModel>(SERIE_EXTRA) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySerieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSerieInformations()
    }

    private fun setupSerieInformations() {
        serie?.let { serie ->
            binding.imageView.loadImage(this, serie.imageUrl)
            binding.serieNameTextView.text = serie.name
        }
    }

    companion object {
        const val SERIE_EXTRA = "ACCOUNT_NAME_EXTRA"

        fun getIntent(context: Context, serie: SerieUIModel) =
            Intent(context, SerieDetailsActivity::class.java).apply {
                putExtra(SERIE_EXTRA, serie)
            }
    }
}