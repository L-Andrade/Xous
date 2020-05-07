package com.andradel.xous.search.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.commonmodels.internal.Show
import com.andradel.xous.commonui.diffs.ItemDiffUtils
import com.andradel.xous.commonui.viewholders.ShowViewHolder
import com.andradel.xous.core.util.extensions.inflate
import com.andradel.xous.search.R
import com.andradel.xous.search.ui.SearchItem

class SearchItemAdapter(
    private val goToShow: (Show) -> Unit
) : ListAdapter<SearchItem, RecyclerView.ViewHolder>(ItemDiffUtils<SearchItem>()) {

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is SearchItem.ShowItem -> SHOW
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = parent.context.inflate(viewType, parent)
        return when (viewType) {
            SHOW -> ShowViewHolder(view)
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is ShowViewHolder -> holder.bind((item as SearchItem.ShowItem).item, goToShow)
        }
    }

    companion object {
        private val SHOW = R.layout.show_viewholder
    }
}