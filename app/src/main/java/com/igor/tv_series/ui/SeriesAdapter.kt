package com.igor.tv_series.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.igor.tv_series.databinding.ItemSerieBinding
import com.igor.tv_series.models.SerieUIModel

class SeriesAdapter(
    private val onItemClicked: (SerieUIModel, Int) -> Unit
) : ListAdapter<SerieUIModel, SeriesAdapter.BdrClientsViewHolder>(SeriesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BdrClientsViewHolder {
        val binding = ItemSerieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BdrClientsViewHolder(
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
        private val binding: ItemSerieBinding,
        private val onItemClicked: (SerieUIModel, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(serie: SerieUIModel, position: Int) {
            with(binding) {
                binding.nameTextView.text = serie.name
                binding.scoreTextView.text = serie.score.toString()

                binding.root.setOnClickListener {
                    onItemClicked(serie, position)
                }
            }
        }
    }
}