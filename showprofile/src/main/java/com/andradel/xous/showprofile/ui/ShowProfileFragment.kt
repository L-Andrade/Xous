package com.andradel.xous.showprofile.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.andradel.xous.commonmodels.internal.Season
import com.andradel.xous.commonmodels.internal.show.Show
import com.andradel.xous.commonui.ViewPager2ParallaxPage
import com.andradel.xous.commonui.extensions.load
import com.andradel.xous.commonui.indicator.setViewPagerAndAdapter
import com.andradel.xous.core.coreComponent
import com.andradel.xous.core.di.ViewModelFactory
import com.andradel.xous.core.util.extensions.goTo
import com.andradel.xous.core.util.extensions.observe
import com.andradel.xous.core.util.extensions.showSnackbar
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.di.DaggerShowProfileComponent
import com.andradel.xous.showprofile.ui.adapter.BackdropAdapter
import com.andradel.xous.showprofile.ui.adapter.ProfileViewAdapter
import kotlinx.android.synthetic.main.show_profile_fragment.*
import javax.inject.Inject

class ShowProfileFragment : Fragment(R.layout.show_profile_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ShowProfileViewModel by viewModels { viewModelFactory }

    private val backdropAdapter by lazy { BackdropAdapter(::goToGallery) }

    private val args: ShowProfileFragmentArgs by navArgs()

    private val profileAdapter by lazy {
        ProfileViewAdapter(::goToShow, ::goToSeason)
    }

    override fun onAttach(context: Context) {
        DaggerShowProfileComponent.builder().coreComponent(coreComponent).build().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        viewModel.getDetails(args.show)

        observe(viewModel.show) { setupShow(it) }

        observe(viewModel.message) { showSnackbar(it) }
    }

    private fun setupView() {
        appBar.addOnOffsetChangedListener(ProfileAppBarOffsetListener)
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        backdropPager.apply {
            adapter = backdropAdapter
            setPageTransformer(ViewPager2ParallaxPage(R.id.backdrop))
        }

        indicator.setViewPagerAndAdapter(backdropPager, backdropAdapter)

        // profileAdapter.stateRestorationPolicy = PREVENT_WHEN_EMPTY
        recyclerView.adapter = profileAdapter
    }

    override fun onDestroyView() {
        appBar.removeOnOffsetChangedListener(ProfileAppBarOffsetListener)
        super.onDestroyView()
    }

    private fun setupShow(state: ProfileState) {
        profileAdapter.submitList(state.items)
        poster.load(state.show.posterUrl, R.color.colorPrimary, R.color.colorAccent, false)
        toolbarTitle.text = state.show.name
        poster.setOnClickListener { goToGallery(state.show.posterUrl.orEmpty()) }
        backdropAdapter.submitList(state.backdrops)
    }

    private fun goToShow(show: Show) {
        goTo(ShowProfileFragmentDirections.showProfileToShowProfile(show))
    }

    private fun goToGallery(clickedImage: String) {
        val images = viewModel.images
        if (images.isNotEmpty()) goTo(ShowProfileFragmentDirections.showProfileToGallery(images, clickedImage))
    }

    private fun goToSeason(season: Season) {
        goTo(ShowProfileFragmentDirections.showProfileToSeason(args.show, season))
    }
}
