package com.andradel.xous.search.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.andradel.xous.commonmodels.internal.show.Show
import com.andradel.xous.commonui.diffs.ItemDiffUtils
import com.andradel.xous.commonui.viewholders.ShowViewHolder
import com.andradel.xous.core.util.extensions.inflate
import com.andradel.xous.search.R
import com.andradel.xous.search.ui.SearchItem

class SearchShowAdapter(
    private val goToShow: (Show) -> Unit
) : ListAdapter<SearchItem.ShowItem, ShowViewHolder>(ItemDiffUtils<SearchItem.ShowItem>()) {

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(getItem(position).item, goToShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        return ShowViewHolder(parent.context.inflate(R.layout.show_viewholder, parent))
    }
}