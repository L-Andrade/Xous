package com.andradel.xous.showprofile.ui.season

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.andradel.xous.commonmodels.internal.Season
import com.andradel.xous.core.coreComponent
import com.andradel.xous.core.di.ViewModelFactory
import com.andradel.xous.core.util.extensions.goTo
import com.andradel.xous.core.util.extensions.observe
import com.andradel.xous.core.util.extensions.showSnackbar
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.di.DaggerShowProfileComponent
import com.andradel.xous.showprofile.ui.season.adapter.SeasonProfileAdapter
import kotlinx.android.synthetic.main.season_fragment.*
import javax.inject.Inject

class SeasonFragment : Fragment(R.layout.season_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val seasonProfileAdapter by lazy { SeasonProfileAdapter(::goToGallery) }

    private val viewModel: SeasonViewModel by viewModels { viewModelFactory }

    private val args: SeasonFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        DaggerShowProfileComponent.builder().coreComponent(coreComponent).build().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val season = args.season

        setupView()
        setupSeason(season)
        viewModel.getDetails(args.show, season)

        observe(viewModel.details) { seasonProfileAdapter.setSeason(season, it) }

        observe(viewModel.message) { showSnackbar(it) }
    }

    private fun setupSeason(season: Season) {
        toolbar.title = season.name
        seasonProfileAdapter.setSeason(season)
    }

    private fun setupView() {
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        recyclerView.apply {
            adapter = seasonProfileAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun goToGallery(clickedImage: String?) {
        clickedImage ?: return
        goTo(SeasonFragmentDirections.seasonToGallery(arrayOf(clickedImage), clickedImage))
    }
}
