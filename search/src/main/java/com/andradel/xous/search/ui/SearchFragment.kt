package com.andradel.xous.search.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import com.andradel.xous.search.R
import com.andradel.xous.search.ui.adapter.SearchFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseSearchFragment(R.layout.fragment_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    override fun setupView() {
        searchPager.adapter = SearchFragmentAdapter(this)
        TabLayoutMediator(tabLayout, searchPager) { tab, _ ->
            tab.text = "Title"
        }.attach()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // TODO: Don't query every change, query if no changes to text in 300ms or something
                viewModel.query = newText
                return true
            }
        })
    }

    override fun onDestroyView() {
        // Hide keyboard
        rootView.requestFocus()
        super.onDestroyView()
    }
}