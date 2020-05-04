package com.andradel.xous.home.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.core.util.diffs.ItemDiffUtils
import com.andradel.xous.core.util.extensions.inflate
import com.andradel.xous.home.R
import com.andradel.xous.home.ui.model.ShowItem

class ShowAdapter(
    private val goToShow: (Show) -> Unit
) : ListAdapter<ShowItem, RecyclerView.ViewHolder>(ItemDiffUtils<ShowItem>()) {

    private val recentlyViewedAdapter = RecentlyViewedAdapter(goToShow)

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is ShowItem.Header -> HEADER
        is ShowItem.Item -> SHOW
        is ShowItem.RecentlyViewedList -> RECENTLY_VIEWED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = parent.context.inflate(viewType, parent)
        return when (viewType) {
            HEADER -> HeaderViewHolder(view)
            SHOW -> ShowViewHolder(view)
            RECENTLY_VIEWED -> RecentlyViewedViewHolder(view)
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is HeaderViewHolder -> holder.bind(item as ShowItem.Header)
            is ShowViewHolder -> holder.bind((item as ShowItem.Item).show, goToShow)
            is RecentlyViewedViewHolder -> holder.bind(recentlyViewedAdapter)
        }
    }

    fun submitRecentlyViewedList(list: List<Show>) {
        recentlyViewedAdapter.submitList(list)
    }

    companion object {
        private val HEADER = R.layout.header_viewholder
        private val SHOW = R.layout.show_viewholder
        private val RECENTLY_VIEWED = R.layout.recently_viewed_viewholder
    }
}