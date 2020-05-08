package com.andradel.xous.search.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.andradel.xous.core.util.extensions.hideKeyboard
import com.andradel.xous.core.util.extensions.observe
import com.andradel.xous.core.util.extensions.showSnackbar
import com.andradel.xous.search.R
import com.andradel.xous.search.ui.adapter.SearchFragmentAdapter
import com.andradel.xous.search.ui.state.ViewSearchState
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class SearchFragment : BaseSearchFragment(R.layout.fragment_search) {

    private var queryJob: Job? = null
        set(value) {
            field?.cancel()
            field = value
        }

    private val searchTabs by lazy {
        listOf(
            getString(R.string.shows_tab),
            getString(R.string.movies_tab),
            getString(R.string.people_tab)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.message) { showSnackbar(it) }
    }

    override fun setupView() {
        searchPager.adapter = SearchFragmentAdapter(this, searchTabs)
        TabLayoutMediator(tabLayout, searchPager) { tab, position ->
            tab.text = searchTabs[position]
        }.attach()

        searchPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) fabFilters.hide() else fabFilters.show()
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                updateQuery(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                updateQuery(newText)
                return true
            }
        })
    }

    override fun onLoading() {
        loading.show()
    }

    override fun onEmpty() {
        loading.hide()
    }

    override fun onItems(state: ViewSearchState.Items) {
        loading.hide()
    }

    private fun updateQuery(newText: String) {
        queryJob = lifecycleScope.launchWhenResumed {
            delay(DELAY_MILLIS)
            viewModel.query = newText
        }
    }

    override fun onDestroyView() {
        hideKeyboard()
        super.onDestroyView()
    }

    companion object {
        private const val DELAY_MILLIS = 350L
    }
}