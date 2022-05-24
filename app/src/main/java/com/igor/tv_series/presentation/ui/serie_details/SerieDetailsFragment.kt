package com.igor.tv_series.presentation.ui.serie_details

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.navigation.fragment.findNavController
import com.igor.tv_series.R
import com.igor.tv_series.databinding.FragmentSerieDetailsBinding
import com.igor.tv_series.presentation.helpers.loadImage
import com.igor.tv_series.presentation.models.SerieUIModel
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class SerieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentSerieDetailsBinding
    private val serieDetailsViewModel: SerieDetailsViewModel by viewModel()

    private var serie: SerieUIModel? = null
    private var adapter: EpisodesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSerieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            serie = it.getParcelable("SERIE")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()

        setupListeners()

        setupSerieInformations()

        setupAdapter()

        serie?.id?.let {
            serieDetailsViewModel.fetchSeasons(it)
        }
    }

    private fun setupListeners() {
        binding.seasonSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                serieDetailsViewModel.setSeason(position)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) = Unit
        }

        binding.favoriteStarImageView.setOnClickListener {
            serie?.let { serie ->
                if (serie.isFavorite) {
                    serieDetailsViewModel.deleteSerie(serie)
                    serie.isFavorite = false
                    binding.favoriteStarImageView.setImageResource(R.drawable.ic_baseline_star_border_24)
                } else {
                    serieDetailsViewModel.favoriteSerie(serie)
                    serie.isFavorite = true
                    binding.favoriteStarImageView.setImageResource(R.drawable.ic_baseline_star_24)
                }
            }
        }
    }

    private fun setupObservers() {
        serieDetailsViewModel.episodes.observe(viewLifecycleOwner) { episodes ->
            adapter?.submitList(episodes)
        }

        serieDetailsViewModel.seasons.observe(viewLifecycleOwner) { seasons ->
            setupSpinner(seasons.map { it.number })
        }

        serieDetailsViewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            serie?.isFavorite = isFavorite
            if (serie?.isFavorite == true) {
                binding.favoriteStarImageView.setImageResource(R.drawable.ic_baseline_star_24)
            } else {
                binding.favoriteStarImageView.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
        }
    }

    private fun setupAdapter() {
        binding.episodesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = EpisodesAdapter() { episode, _ ->
            val bundle = Bundle().apply {
                this.putParcelable("EPISODE", episode)
            }
            findNavController().navigate(
                R.id.action_serieDetailsFragment_to_episodeFragment,
                bundle
            )
        }
        binding.episodesRecyclerView.adapter = adapter
    }

    private fun setupSpinner(list: List<String>) {
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            list
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.seasonSpinner.adapter = adapter
    }

    private fun setupSerieInformations() {
        serie?.let { serie ->
            serieDetailsViewModel.isAFavoriteSerie(serie.id)
            binding.imageView.loadImage(requireContext(), serie.imageUrl)
            binding.serieNameTextView.text = serie.name
            binding.airDateTextView.text = serie.premiered
            binding.airTimeTextView.text = serie.ended
            binding.genresTextView.text = serie.genres
            binding.summaryTextView.text = Html.fromHtml(serie.summary, Html.FROM_HTML_MODE_COMPACT)
        }
    }
}