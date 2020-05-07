package com.andradel.xous.search.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.andradel.xous.core.coreComponent
import com.andradel.xous.core.di.ViewModelFactory
import com.andradel.xous.search.R
import com.andradel.xous.search.di.DaggerSearchComponent
import com.andradel.xous.search.ui.adapter.SearchFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : Fragment(R.layout.fragment_search) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: SearchViewModel by navGraphViewModels(R.id.search) { viewModelFactory }

    override fun onAttach(context: Context) {
        DaggerSearchComponent.builder().coreComponent(coreComponent).build().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchPager.adapter = SearchFragmentAdapter(this)
        TabLayoutMediator(tabLayout, searchPager) { tab, _ ->
            tab.text = "Title"
        }.attach()
    }

    override fun onDestroyView() {
        // Hide keyboard
        rootView.requestFocus()
        super.onDestroyView()
    }
}