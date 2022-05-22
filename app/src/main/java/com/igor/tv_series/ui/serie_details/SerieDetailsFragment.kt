package com.igor.tv_series.ui.serie_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.igor.tv_series.R
import com.igor.tv_series.databinding.FragmentSerieDetailsBinding
import com.igor.tv_series.helpers.loadImage
import com.igor.tv_series.models.EpisodeUIModel
import com.igor.tv_series.models.SerieUIModel

class SerieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentSerieDetailsBinding

    private var serie: SerieUIModel? = null
    private var episode: EpisodeUIModel = EpisodeUIModel("Pilot", 1, 1, "When the residents of Chester's Mill find themselves trapped under a massive transparent dome with no way out, they struggle to survive as resources rapidly dwindle and panic quickly escalates.",
    "https://static.tvmaze.com/uploads/images/medium_landscape/1/4388.jpg")

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

        setupListeners()

        setupSerieInformations()
    }

    private fun setupListeners() {
        binding.imageView.setOnClickListener {
            val bundle = Bundle().apply {
                this.putParcelable("EPISODE", episode)
            }
            findNavController().navigate(R.id.action_serieDetailsFragment_to_episodeFragment, bundle)
        }
    }

    private fun setupSerieInformations() {
        serie?.let { serie ->
            binding.imageView.loadImage(requireContext(), serie.imageUrl)
            binding.serieNameTextView.text = serie.name
        }
    }
}