package com.igor.tv_series.ui.serie_details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.igor.tv_series.R
import com.igor.tv_series.databinding.ActivitySerieDetailsBinding
import com.igor.tv_series.models.SerieUIModel

class SerieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySerieDetailsBinding

    private val serie by lazy { intent?.extras?.getParcelable<SerieUIModel>("SERIE") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySerieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFragment()
    }

    private fun setupFragment() {
        val navHost = supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        val bundle = Bundle().apply {
            this.putParcelable("SERIE", serie)
        }
        navHost.navController.setGraph(R.navigation.nav_graph_serie_details, bundle)
    }

    companion object {
        fun getIntent(context: Context, serie: SerieUIModel) =
            Intent(context, SerieDetailsActivity::class.java).apply {
                putExtra("SERIE", serie)
            }
    }
}