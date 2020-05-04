package com.andradel.xous.showprofile.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.common_models.internal.BaseShow
import com.andradel.xous.common_models.internal.Season
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.core.stringresolver.StringResolver
import com.andradel.xous.core.util.diffs.ItemDiffUtils
import com.andradel.xous.core.util.extensions.inflate
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.model.FullShow
import com.andradel.xous.showprofile.ui.adapter.viewholders.ListViewHolder
import com.andradel.xous.showprofile.ui.adapter.viewholders.OverviewViewHolder

class ProfileViewAdapter(
    private val resolver: StringResolver,
    private val goToShow: (Show) -> Unit,
    private val goToSeason: (Season) -> Unit
) : ListAdapter<ProfileItem, RecyclerView.ViewHolder>(ItemDiffUtils<ProfileItem>()) {

    fun setShow(show: BaseShow) {
        val list = when (show) {
            is Show -> listOf(ProfileItem.Overview(show))
            is FullShow -> listOf(
                ProfileItem.Overview(show),
                ProfileItem.Content.People(resolver[R.string.creators_and_crew], show.crew),
                ProfileItem.Content.People(resolver[R.string.cast], show.cast),
                ProfileItem.Content.Seasons(resolver[R.string.seasons], show.seasons, goToSeason),
                ProfileItem.Content.SimilarShows(
                    resolver[R.string.similar_shows],
                    show.similarShows.items,
                    goToShow
                )
            )
            else -> throw IllegalArgumentException("Unknown show type")
        }
        submitList(list)
    }

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
            holder is ListViewHolder && item is ProfileItem.Content -> holder.bind(
                item.title,
                item.adapter
            )
        }
    }

    companion object {
        private val LIST = R.layout.list_viewholder
        private val OVERVIEW = R.layout.overview_viewholder
    }
}