package com.andradel.xous.search.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.core.util.diffs.ItemDiffUtils
import com.andradel.xous.search.ui.SearchItem

class SearchItemAdapter(

) : ListAdapter<SearchItem, RecyclerView.ViewHolder>(ItemDiffUtils<SearchItem>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}