package com.andradel.xous.home.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.andradel.xous.commonmodels.internal.show.Show
import com.andradel.xous.commonui.diffs.ItemDiffUtils
import com.andradel.xous.commonui.viewholders.ShowViewHolder
import com.andradel.xous.core.util.extensions.inflate
import com.andradel.xous.home.R

class RecentlyViewedAdapter(
    private val goToShow: (Show) -> Unit
) : ListAdapter<Show, ShowViewHolder>(ItemDiffUtils<Show>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder =
        ShowViewHolder(parent.context.inflate(R.layout.show_viewholder, parent))

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(getItem(position), goToShow)
    }
}