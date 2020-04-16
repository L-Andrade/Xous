package com.andradel.xous.showprofile.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.common_models.internal.BaseShow
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.core.stringresolver.StringResolver
import com.andradel.xous.core.util.extensions.inflate
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.model.FullShow
import com.andradel.xous.showprofile.ui.adapter.viewholders.ListViewHolder
import com.andradel.xous.showprofile.ui.adapter.viewholders.OverviewViewHolder

class ProfileViewAdapter(
    show: BaseShow,
    resolver: StringResolver,
    goToShow: (Show) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: List<ProfileItem> = buildFromFullShow(show, resolver, goToShow)

    private fun buildFromFullShow(
        show: BaseShow,
        resolver: StringResolver,
        goToShow: (Show) -> Unit
    ): List<ProfileItem> {
        return when (show) {
            is Show -> listOf(ProfileItem.Overview(show))
            is FullShow -> listOf(
                ProfileItem.Overview(show),
                ProfileItem.Content.Creators(resolver[R.string.creators], show.createdBy),
                ProfileItem.Content.Seasons(resolver[R.string.seasons], show.seasons),
                ProfileItem.Content.SimilarShows(
                    resolver[R.string.similar_shows],
                    show.similarShows.items,
                    goToShow
                )
            )
            else -> throw IllegalArgumentException("Unknown show type")
        }
    }

    override fun getItemViewType(position: Int): Int = when (data[position]) {
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

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
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