package com.andradel.xous.showprofile.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.common_models.internal.BaseShow
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.core.coreComponent
import com.andradel.xous.core.di.ViewModelFactory
import com.andradel.xous.core.util.extensions.getHtmlSpannedString
import com.andradel.xous.core.util.extensions.goTo
import com.andradel.xous.core.util.extensions.loadWithFade
import com.andradel.xous.core.util.extensions.observe
import com.andradel.xous.core.util.extensions.showSnackbar
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.di.DaggerShowProfileComponent
import com.andradel.xous.showprofile.model.FullShow
import com.andradel.xous.showprofile.ui.adapter.CreatorAdapter
import com.andradel.xous.showprofile.ui.adapter.SeasonAdapter
import com.andradel.xous.showprofile.ui.adapter.SimilarShowAdapter
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.show_profile_fragment.*
import javax.inject.Inject

class ShowProfileFragment : Fragment(R.layout.show_profile_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ShowProfileViewModel by viewModels {
        viewModelFactory
    }
    private val creatorAdapter: CreatorAdapter by lazy { CreatorAdapter() }
    private val similarShowAdapter: SimilarShowAdapter by lazy { SimilarShowAdapter(::goToShow) }
    private val seasonAdapter: SeasonAdapter by lazy { SeasonAdapter() }

    private val args: ShowProfileFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        DaggerShowProfileComponent.builder().coreComponent(coreComponent).build().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val show = args.show
        setupView(show)
        viewModel.getDetails(show)

        observe(viewModel.details) {
            setupWithDetails(it)
        }

        observe(viewModel.message) {
            showSnackbar(it)
        }
    }

    private fun setupView(show: Show) {
        setupRecyclerViews()
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, offset ->
            val totalScroll = appBarLayout.totalScrollRange.toFloat()
            val alpha = (totalScroll + offset) / totalScroll
            posterCard.alpha = alpha
            toolbar.navigationIcon?.alpha = ((1 - alpha) * 255).toInt()
        })
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        setupShow(show)
    }

    private fun setupRecyclerViews() {
        creators.apply {
            adapter = creatorAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        similarShows.apply {
            adapter = similarShowAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        seasons.apply {
            adapter = seasonAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    private fun setupWithDetails(fullShow: FullShow) {
        setupShow(fullShow)
        numberOfSeasons.text =
            getHtmlSpannedString(R.string.number_of_seasons, fullShow.numberOfSeasons)
        numberOfEpisodes.text =
            getHtmlSpannedString(R.string.number_of_episodes, fullShow.numberOfEpisodes)

        // Setup creators
        creatorsTitle.isVisible = fullShow.createdBy.isNotEmpty()
        creatorAdapter.submitList(fullShow.createdBy)
        seasonAdapter.submitList(fullShow.seasons)
        similarShowAdapter.submitList(fullShow.similarShows.items)
    }

    private fun setupShow(show: BaseShow) {
        backdrop.loadWithFade(show.backdropUrl)
        poster.loadWithFade(show.posterUrl)
        toolbar.title = show.name
        rating.text = show.rating.toString()
        firstAired.text = getHtmlSpannedString(R.string.first_aired, show.firstAired)
        overview.text = show.overview
    }

    private fun goToShow(show: Show) {
        goTo(ShowProfileFragmentDirections.showProfileToShowProfile(show))
    }
}
