package com.andradel.xous.search.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.andradel.xous.search.ui.fragments.ShowSearchFragment

class SearchFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 1

    override fun createFragment(position: Int): Fragment {
        return ShowSearchFragment()
    }
}