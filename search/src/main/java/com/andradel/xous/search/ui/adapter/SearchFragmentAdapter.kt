package com.andradel.xous.search.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.andradel.xous.search.ui.fragments.PersonSearchFragment
import com.andradel.xous.search.ui.fragments.ShowSearchFragment

class SearchFragmentAdapter(
    fragment: Fragment,
    private val searchTabs: List<String>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = searchTabs.size

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> ShowSearchFragment()
        1 -> ShowSearchFragment()
        2 -> PersonSearchFragment()
        else -> throw IllegalArgumentException("Unknown search type")
    }
}