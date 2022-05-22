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
) : ListAdapter<SerieUIModel, SeriesAdapter.BdrClientsViewHolder>(SeriesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BdrClientsViewHolder {
        val binding = ItemSerieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BdrClientsViewHolder(
            context = context,
            binding = binding,
            onItemClicked = onItemClicked
        )
    }

    override fun onBindViewHolder(holder: BdrClientsViewHolder, position: Int) {
        getItem(position).let { serie ->
            holder.bind(serie, position)
        }
    }

    inner class BdrClientsViewHolder(
        private val context: Context,
        private val binding: ItemSerieBinding,
        private val onItemClicked: (SerieUIModel, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(serie: SerieUIModel, position: Int) {
            with(binding) {
                binding.nameTextView.text = serie.name
                binding.scoreTextView.text = serie.score.toPercent()
                binding.posterImageView.loadImage(context, serie.imageUrl)

                binding.root.setOnClickListener {
                    onItemClicked(serie, position)
                }
            }
        }
    }
}