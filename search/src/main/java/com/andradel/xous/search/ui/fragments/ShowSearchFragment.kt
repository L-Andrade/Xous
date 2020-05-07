package com.andradel.xous.search.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.andradel.xous.commonmodels.internal.Show
import com.andradel.xous.core.util.extensions.goTo
import com.andradel.xous.core.util.extensions.observe
import com.andradel.xous.search.R
import com.andradel.xous.search.ui.BaseSearchFragment
import com.andradel.xous.search.ui.SearchFragmentDirections
import com.andradel.xous.search.ui.adapter.SearchItemAdapter
import com.andradel.xous.search.ui.state.ViewSearchState
import kotlinx.android.synthetic.main.fragment_show_search.*

class ShowSearchFragment : BaseSearchFragment(R.layout.fragment_show_search) {

    private val searchItemAdapter by lazy { SearchItemAdapter(::goToShow) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observe(viewModel.viewState) { state ->
            when (state) {
                ViewSearchState.Loading -> {
                }
                ViewSearchState.Empty -> {
                }
                is ViewSearchState.Items -> searchItemAdapter.submitList(state.items)
            }
        }
    }

    override fun setupView() {
        recyclerView.apply {
            adapter = searchItemAdapter
            layoutManager = GridLayoutManager(requireContext(), 4)
        }
    }

    private fun goToShow(show: Show) {
        // Apparently navigating from a VP to other places is weird... what a pain!
        parentFragment?.goTo(SearchFragmentDirections.searchToShowProfile(show))
    }
}