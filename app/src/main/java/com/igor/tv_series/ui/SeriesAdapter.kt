package com.igor.tv_series.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.igor.tv_series.databinding.ItemSerieBinding
import com.igor.tv_series.helpers.loadImage
import com.igor.tv_series.helpers.toPercent
import com.igor.tv_series.models.SerieUIModel

class SeriesAdapter(
    private val context: Context,
    private val onItemClicked: (SerieUIModel, Int) -> Unit
) : ListAdapter<SerieUIModel, SeriesAdapter.SeriesAdapterViewHolder>(SeriesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesAdapterViewHolder {
        val binding = ItemSerieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SeriesAdapterViewHolder(
            context = context,
            binding = binding,
            onItemClicked = onItemClicked
        )
    }

    override fun onBindViewHolder(holder: SeriesAdapterViewHolder, position: Int) {
        getItem(position).let { serie ->
            holder.bind(serie, position)
        }
    }

    inner class SeriesAdapterViewHolder(
        private val context: Context,
        private val binding: ItemSerieBinding,
        private val onItemClicked: (SerieUIModel, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(serie: SerieUIModel, position: Int) {
            with(binding) {
                nameTextView.text = serie.name
                scoreTextView.text = serie.score.toPercent()
                posterImageView.loadImage(context, serie.imageUrl)

                root.setOnClickListener {
                    onItemClicked(serie, position)
                }
            }
        }
    }
}