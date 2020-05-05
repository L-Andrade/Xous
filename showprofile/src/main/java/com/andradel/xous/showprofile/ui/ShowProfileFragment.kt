package com.andradel.xous.showprofile.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.andradel.xous.commonmodels.internal.BaseShow
import com.andradel.xous.commonmodels.internal.Season
import com.andradel.xous.commonmodels.internal.Show
import com.andradel.xous.commonui.indicator.setViewPagerAndAdapter
import com.andradel.xous.core.coreComponent
import com.andradel.xous.core.di.ViewModelFactory
import com.andradel.xous.core.stringresolver.StringResolver
import com.andradel.xous.core.util.extensions.goTo
import com.andradel.xous.core.util.extensions.loadWithFade
import com.andradel.xous.core.util.extensions.observe
import com.andradel.xous.core.util.extensions.showSnackbar
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.di.DaggerShowProfileComponent
import com.andradel.xous.showprofile.model.FullShow
import com.andradel.xous.showprofile.ui.adapter.BackdropAdapter
import com.andradel.xous.showprofile.ui.adapter.BackdropParallax
import com.andradel.xous.showprofile.ui.adapter.ProfileViewAdapter
import kotlinx.android.synthetic.main.profile_fragment.*
import javax.inject.Inject

class ShowProfileFragment : Fragment(R.layout.profile_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var stringResolver: StringResolver

    private val viewModel: ShowProfileViewModel by viewModels { viewModelFactory }

    private val backdropAdapter by lazy { BackdropAdapter(::goToGallery) }

    private val args: ShowProfileFragmentArgs by navArgs()

    private val profileAdapter by lazy {
        ProfileViewAdapter(stringResolver, ::goToShow, ::goToSeason)
    }

    override fun onAttach(context: Context) {
        DaggerShowProfileComponent.builder().coreComponent(coreComponent).build().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val show = args.show
        setupView()
        viewModel.getDetails(show)

        observe(viewModel.show) { setupShow(it) }

        observe(viewModel.message) { showSnackbar(it) }
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
            adapter = profileAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupShow(show: BaseShow) {
        profileAdapter.setShow(show)
        poster.loadWithFade(show.posterUrl)
        toolbar.title = show.name
        poster.setOnClickListener { goToGallery(show.posterUrl.orEmpty()) }

        when {
            show is Show && show.backdropUrl != null ->
                backdropAdapter.submitList(listOf(show.backdropUrl))
            show is FullShow -> backdropAdapter.submitList(show.backdrops)
        }
    }

    private fun goToShow(show: Show) {
        goTo(ShowProfileFragmentDirections.showProfileToShowProfile(show))
    }

    private fun goToGallery(clickedImage: String) {
        val images = viewModel.images
        if (images.isNotEmpty())
            goTo(ShowProfileFragmentDirections.showProfileToGallery(images, clickedImage))
    }

    private fun goToSeason(season: Season) {
        goTo(ShowProfileFragmentDirections.showProfileToSeason(args.show, season))
    }
}
