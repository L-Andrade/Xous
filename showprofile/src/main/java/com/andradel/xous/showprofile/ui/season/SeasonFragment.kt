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
import com.andradel.xous.commonui.indicator.setViewPagerAndAdapter
import com.andradel.xous.core.coreComponent
import com.andradel.xous.core.di.ViewModelFactory
import com.andradel.xous.core.util.extensions.goTo
import com.andradel.xous.core.util.extensions.loadWithFade
import com.andradel.xous.core.util.extensions.observe
import com.andradel.xous.core.util.extensions.showSnackbar
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.di.DaggerShowProfileComponent
import com.andradel.xous.showprofile.ui.ProfileAppBarOffsetListener
import com.andradel.xous.showprofile.ui.adapter.BackdropAdapter
import com.andradel.xous.showprofile.ui.adapter.BackdropParallax
import com.andradel.xous.showprofile.ui.season.adapter.SeasonProfileAdapter
import kotlinx.android.synthetic.main.profile_fragment.*
import javax.inject.Inject

class SeasonFragment : Fragment(R.layout.profile_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val backdropAdapter by lazy { BackdropAdapter(::goToGallery) }

    private val seasonProfileAdapter by lazy { SeasonProfileAdapter() }

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
        poster.loadWithFade(season.posterUrl)
        toolbar.title = season.name
        poster.setOnClickListener {
            goToGallery(season.posterUrl.orEmpty())
        }
        seasonProfileAdapter.setSeason(season)
    }

    private fun setupView() {
        appBar.addOnOffsetChangedListener(ProfileAppBarOffsetListener)
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        backdropPager.apply {
            adapter = backdropAdapter
            setPageTransformer(BackdropParallax)
        }

        indicator.setViewPagerAndAdapter(backdropPager, backdropAdapter)

        recyclerView.apply {
            adapter = seasonProfileAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun goToGallery(clickedImage: String) {
        // val images = viewModel.images
        // if (images.isNotEmpty())
        goTo(SeasonFragmentDirections.seasonToGallery(arrayOf(), clickedImage))
    }
}
