package com.andradel.xous.home.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.core.coreComponent
import com.andradel.xous.core.di.ViewModelFactory
import com.andradel.xous.core.util.exhaustive
import com.andradel.xous.core.util.extensions.*
import com.andradel.xous.home.R
import com.andradel.xous.home.di.DaggerHomeComponent
import com.andradel.xous.home.ui.adapter.ShowAdapter
import com.andradel.xous.home.ui.model.HomeState
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.home_fragment) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }
    private val showAdapter: ShowAdapter by lazy { ShowAdapter(::goToShow) }
    // private val recentlyViewedAdapter: RecentlyViewedAdapter by lazy { RecentlyViewedAdapter(::goToShow) }

    override fun onAttach(context: Context) {
        DaggerHomeComponent.builder().coreComponent(coreComponent).build().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        refresh.setOnClickListener {
            viewModel.getAllShows()
        }

        observe(viewModel.state) {
            when (it) {
                HomeState.Loading -> onLoading()
                is HomeState.ShowLists -> onSuccess(it)
                is HomeState.Empty -> onEmpty(it)
            }.exhaustive
        }
        observe(viewModel.message) {
            showSnackbar(it)
        }
        observe(viewModel.recentlyViewed) {
            showAdapter.submitRecentlyViewedList(it)
        }
    }

    private fun onLoading() {
        emptyState.animateOut()
        loading.animateIn()
    }

    private fun setupRecyclerView() {
        // recentlyViewed.adapter = recentlyViewedAdapter
        shows.apply {
            adapter = showAdapter
            layoutManager = FlexboxLayoutManager(requireContext()).also {
                it.justifyContent = JustifyContent.CENTER
            }
        }
    }

    private fun onEmpty(data: HomeState.Empty) {
        loading.animateOut()
        emptyState.animateIn()
        showAdapter.submitList(data.shows)
    }

    private fun onSuccess(data: HomeState.ShowLists) {
        loading.animateOut()
        emptyState.isVisible = false
        showAdapter.submitList(data.shows)
    }

    private fun goToShow(show: Show) {
        goTo(HomeFragmentDirections.actionHomeFragmentToShowProfileFragment(show))
    }
}
