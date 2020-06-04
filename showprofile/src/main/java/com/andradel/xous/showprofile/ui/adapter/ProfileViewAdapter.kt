package com.andradel.xous.showprofile.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.commonmodels.internal.Season
import com.andradel.xous.commonmodels.internal.show.BaseShow
import com.andradel.xous.commonmodels.internal.show.Show
import com.andradel.xous.commonui.diffs.ItemDiffUtils
import com.andradel.xous.core.stringresolver.StringResolver
import com.andradel.xous.core.util.extensions.inflate
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.model.FullShow
import com.andradel.xous.showprofile.ui.adapter.viewholders.ListViewHolder
import com.andradel.xous.showprofile.ui.adapter.viewholders.OverviewViewHolder

class ProfileViewAdapter(
    private val goToShow: (Show) -> Unit,
    private val goToSeason: (Season) -> Unit
) : ListAdapter<ProfileItem, RecyclerView.ViewHolder>(ItemDiffUtils<ProfileItem>()) {

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is ProfileItem.Overview -> OVERVIEW
        is ProfileItem.Content -> LIST
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = parent.context.inflate(viewType, parent)
        return when (viewType) {
            OVERVIEW -> OverviewViewHolder(view)
            LIST -> ListViewHolder(view)
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when {
            holder is OverviewViewHolder -> holder.bind((item as ProfileItem.Overview).show)
            holder is ListViewHolder && item is ProfileItem.Content.SimilarShows ->
                holder.bind(item, goToShow)
            holder is ListViewHolder && item is ProfileItem.Content.People<*> ->
                holder.bind(item) {}
            holder is ListViewHolder && item is ProfileItem.Content.Seasons ->
                holder.bind(item, goToSeason)
        }
    }

    companion object {
        private val LIST = R.layout.list_viewholder
        private val OVERVIEW = R.layout.overview_viewholder
    }
}