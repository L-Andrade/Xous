package com.andradel.xous.home.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.andradel.xous.commonmodels.internal.show.Show
import com.andradel.xous.commonui.extensions.animateIn
import com.andradel.xous.commonui.extensions.animateOut
import com.andradel.xous.core.di.ViewModelFactory
import com.andradel.xous.core.util.extensions.goTo
import com.andradel.xous.core.util.extensions.observe
import com.andradel.xous.core.util.extensions.showSnackbar
import com.andradel.xous.home.R
import com.andradel.xous.home.di.HomeComponent
import com.andradel.xous.home.ui.adapter.ShowAdapter
import com.andradel.xous.scopes.ComponentHolder
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.home_fragment) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        ComponentHolder.component<HomeComponent>().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val showAdapter = ShowAdapter(::goToShow)
        setupRecyclerView(showAdapter)
        refresh.setOnClickListener {
            viewModel.getAllShows()
        }

        setupObservers(showAdapter)
    }

    private fun setupObservers(showAdapter: ShowAdapter) {
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
            loading.show()
        } else loading.hide()
    }

    private fun setupRecyclerView(showAdapter: ShowAdapter) {
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
        goTo(HomeFragmentDirections.homeToShowProfile(show))
    }
}
