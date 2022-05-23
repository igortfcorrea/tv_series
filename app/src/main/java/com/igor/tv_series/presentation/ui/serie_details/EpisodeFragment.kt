package com.igor.tv_series.presentation.ui.serie_details

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.igor.tv_series.databinding.FragmentEpisodeBinding
import com.igor.tv_series.presentation.helpers.loadImage
import com.igor.tv_series.presentation.models.EpisodeUIModel

private const val EPISODE = "EPISODE"

class EpisodeFragment : Fragment() {

    private lateinit var binding: FragmentEpisodeBinding

    private var episode: EpisodeUIModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            episode = it.getParcelable(EPISODE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupInformations()
    }

    private fun setupInformations() {
        episode?.imageUrl?.let { url ->
            binding.episodeImageView.loadImage(requireContext(), url)
        }
        binding.episodeNameTextView.text = episode?.name
        binding.episodesTextView.text = "Season ${episode?.season} - Episode ${episode?.number}"
        binding.episodeSummaryTextView.text =
            Html.fromHtml(episode?.summary, Html.FROM_HTML_MODE_COMPACT)
    }
}
