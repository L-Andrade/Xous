package com.andradel.xous.showprofile.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.andradel.xous.common_models.internal.BaseShow
import com.andradel.xous.common_models.internal.Show
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
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.show_profile_fragment.*
import javax.inject.Inject

class ShowProfileFragment : Fragment(R.layout.show_profile_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var stringResolver: StringResolver

    private val viewModel: ShowProfileViewModel by viewModels {
        viewModelFactory
    }

    private val backdropAdapter by lazy { BackdropAdapter(::goToGallery) }

    private val args: ShowProfileFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        DaggerShowProfileComponent.builder().coreComponent(coreComponent).build().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val show = args.show
        setupView()
        setupShow(show)
        viewModel.getDetails(show)

        observe(viewModel.details) {
            setupWithDetails(it)
        }

        observe(viewModel.message) {
            showSnackbar(it)
        }
    }

    private fun setupView() {
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, offset ->
            val totalScroll = appBarLayout.totalScrollRange.toFloat()
            val alpha = (totalScroll + offset) / totalScroll
            posterCard.alpha = alpha
            toolbar.navigationIcon?.alpha = ((1 - alpha) * 255).toInt()
        })
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        backdropPager.adapter = backdropAdapter
        indicator.setViewPager(backdropPager)
        backdropAdapter.registerAdapterDataObserver(indicator.adapterDataObserver)
        backdropPager.setPageTransformer(BackdropParallax)
    }

    private fun setupWithDetails(fullShow: FullShow) {
        backdropAdapter.submitList(fullShow.backdrops)
        recyclerView.apply {
            adapter = ProfileViewAdapter(fullShow, stringResolver, ::goToShow)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupShow(show: BaseShow) {
        backdropAdapter.submitList(listOf(show.backdropUrl))
        poster.loadWithFade(show.posterUrl)
        toolbar.title = show.name
        poster.setOnClickListener {
            goToGallery(show.posterUrl.orEmpty())
        }

        recyclerView.apply {
            adapter = ProfileViewAdapter(show, stringResolver, ::goToShow)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun goToShow(show: Show) {
        goTo(ShowProfileFragmentDirections.showProfileToShowProfile(show))
    }

    private fun goToGallery(clickedImage: String) {
        goTo(ShowProfileFragmentDirections.showProfileToGallery(viewModel.images, clickedImage))
    }
}
