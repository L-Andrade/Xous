package com.andradel.xous.home.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.core.coreComponent
import com.andradel.xous.core.di.ViewModelFactory
import com.andradel.xous.core.util.extensions.*
import com.andradel.xous.home.R
import com.andradel.xous.home.di.DaggerHomeComponent
import com.andradel.xous.home.ui.adapter.ShowAdapter
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

        setupObservers()
    }

    private fun setupObservers() {
        observe(viewModel.loading) {
            onLoading(it)
        }
        observe(viewModel.shows) {
            showAdapter.submitList(it)
        }
        observe(viewModel.message) {
            showSnackbar(it)
        }
        observe(viewModel.recentlyViewed) {
            showAdapter.submitRecentlyViewedList(it)
        }
        observe(viewModel.showEmpty) {
            onEmpty(it)
        }
    }

    private fun onLoading(state: Boolean) {
        if (state) {
            emptyState.animateOut()
            loading.animateIn()
        } else loading.animateOut()
    }

    private fun setupRecyclerView() {
        shows.apply {
            adapter = showAdapter
            layoutManager = FlexboxLayoutManager(requireContext()).also {
                it.justifyContent = JustifyContent.CENTER
            }
        }
    }

    private fun onEmpty(state: Boolean) {
        if (state) emptyState.animateIn() else emptyState.animateOut()
    }

    private fun goToShow(show: Show) {
        goTo(HomeFragmentDirections.actionHomeFragmentToShowProfileFragment(show))
    }
}
