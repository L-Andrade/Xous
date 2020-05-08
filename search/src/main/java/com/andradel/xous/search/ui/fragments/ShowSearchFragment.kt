package com.andradel.xous.search.ui.fragments

import androidx.recyclerview.widget.GridLayoutManager
import com.andradel.xous.commonmodels.internal.Show
import com.andradel.xous.core.util.extensions.goTo
import com.andradel.xous.search.R
import com.andradel.xous.search.ui.BaseSearchFragment
import com.andradel.xous.search.ui.SearchFragmentDirections
import com.andradel.xous.search.ui.adapter.SearchItemAdapter
import com.andradel.xous.search.ui.state.ViewSearchState
import kotlinx.android.synthetic.main.fragment_media_search.*

class ShowSearchFragment : BaseSearchFragment(R.layout.fragment_media_search) {

    private val searchItemAdapter by lazy { SearchItemAdapter(::goToShow) }

    override fun setupView() {
        recyclerView.apply {
            adapter = searchItemAdapter
            layoutManager = GridLayoutManager(requireContext(), 4)
        }
    }

    override fun onLoading() {
        // TODO: Hide empty state
    }

    override fun onEmpty() {
        // TODO: Empty state
    }

    override fun onItems(state: ViewSearchState.Items) {
        searchItemAdapter.submitList(state.items)
    }

    private fun goToShow(show: Show) {
        // Apparently navigating from a VP to other places is weird... what a pain!
        parentFragment?.goTo(SearchFragmentDirections.searchToShowProfile(show))
    }
}