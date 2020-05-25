package com.andradel.xous.search.ui.fragments

import androidx.recyclerview.widget.GridLayoutManager
import com.andradel.xous.commonmodels.internal.person.Person
import com.andradel.xous.search.R
import com.andradel.xous.search.ui.BaseSearchFragment
import com.andradel.xous.search.ui.adapter.SearchPersonAdapter
import com.andradel.xous.search.ui.state.ViewSearchState
import kotlinx.android.synthetic.main.fragment_media_search.*

class PersonSearchFragment : BaseSearchFragment(R.layout.fragment_media_search) {

    private val searchItemAdapter by lazy { SearchPersonAdapter(::goTo) }

    override fun setupView() {
        recyclerView.apply {
            adapter = searchItemAdapter
            layoutManager = GridLayoutManager(
                requireContext(),
                resources.getInteger(R.integer.number_show_grid)
            )
        }
    }

    override fun onLoading() {
        // TODO
    }

    override fun onEmpty() {
        // TODO
    }

    override fun onItems(state: ViewSearchState.Items) {
        searchItemAdapter.submitList(state.people)
    }

    private fun goTo(item: Person) {
        // TODO
    }
}