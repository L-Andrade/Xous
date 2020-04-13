package com.andradel.xous.showprofile.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.common_models.internal.BaseShow
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.core.stringresolver.StringResolver
import com.andradel.xous.core.util.extensions.inflate
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.model.FullShow
import com.andradel.xous.showprofile.ui.adapter.viewholders.HeaderViewHolder
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
                ProfileItem.Header(resolver[R.string.creators]),
                ProfileItem.CreatorList(show.createdBy),
                ProfileItem.Header(resolver[R.string.seasons]),
                ProfileItem.SeasonList(show.seasons),
                ProfileItem.Header(resolver[R.string.similar_shows]),
                ProfileItem.SimilarShowList(show.similarShows.items, goToShow)
            )
            else -> throw IllegalArgumentException("Unknown show type")
        }
    }

    override fun getItemViewType(position: Int): Int = when (data[position]) {
        is ProfileItem.Header -> HEADER
        is ProfileItem.Overview -> OVERVIEW
        is ProfileItem.SimilarShowList -> SIMILAR_SHOW_LIST
        is ProfileItem.CreatorList -> CREATOR_LIST
        is ProfileItem.SeasonList -> SEASON_LIST
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = parent.context.inflate(viewType, parent)
        return when (viewType) {
            HEADER -> HeaderViewHolder(view)
            OVERVIEW -> OverviewViewHolder(view)
            SIMILAR_SHOW_LIST, CREATOR_LIST, SEASON_LIST -> ListViewHolder(view)
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        when {
            holder is HeaderViewHolder -> holder.bind((item as ProfileItem.Header).title)
            holder is OverviewViewHolder -> holder.bind((item as ProfileItem.Overview).show)
            holder is ListViewHolder && item is ProfileItem.SimilarShowList -> holder.bind(item.adapter)
            holder is ListViewHolder && item is ProfileItem.CreatorList -> holder.bind(item.adapter)
            holder is ListViewHolder && item is ProfileItem.SeasonList -> holder.bind(item.adapter)
        }
    }

    companion object {
        private val CREATOR_LIST = R.layout.list_viewholder
        private val SIMILAR_SHOW_LIST = R.layout.list_viewholder
        private val SEASON_LIST = R.layout.list_viewholder
        private val OVERVIEW = R.layout.overview_viewholder
        private val HEADER = R.layout.header_viewholder
    }
}