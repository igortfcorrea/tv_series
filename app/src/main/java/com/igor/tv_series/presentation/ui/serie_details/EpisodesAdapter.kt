package com.igor.tv_series.presentation.ui.serie_details

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.igor.tv_series.databinding.ItemEpisodeBinding
import com.igor.tv_series.presentation.models.EpisodeUIModel

class EpisodesAdapter(
    private val context: Context,
    private val onItemClicked: (EpisodeUIModel, Int) -> Unit
) : ListAdapter<EpisodeUIModel, EpisodesAdapter.EpisodesAdapterViewHolder>(EpisodesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesAdapterViewHolder {
        val binding = ItemEpisodeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EpisodesAdapterViewHolder(
            context = context,
            binding = binding,
            onItemClicked = onItemClicked
        )
    }

    override fun onBindViewHolder(holder: EpisodesAdapterViewHolder, position: Int) {
        getItem(position).let { episode ->
            holder.bind(episode, position)
        }
    }

    inner class EpisodesAdapterViewHolder(
        private val context: Context,
        private val binding: ItemEpisodeBinding,
        private val onItemClicked: (EpisodeUIModel, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(episode: EpisodeUIModel, position: Int) {
            with(binding) {
                episodeNameTextView.text = episode.name
                episodeNumberTextView.text = "${episode.number}."

                root.setOnClickListener {
                    onItemClicked(episode, position)
                }
            }
        }
    }
}