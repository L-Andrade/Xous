package com.andradel.xous.showprofile.ui.season.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.common_models.internal.Season
import com.andradel.xous.core.util.diffs.ItemDiffUtils
import com.andradel.xous.core.util.extensions.inflate
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.model.SeasonDetails
import com.andradel.xous.showprofile.ui.adapter.viewholders.OverviewViewHolder

class SeasonProfileAdapter :
    ListAdapter<SeasonItem, RecyclerView.ViewHolder>(ItemDiffUtils<SeasonItem>()) {

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is SeasonItem.Overview -> OVERVIEW
        is SeasonItem.EpisodeItem -> EPISODE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = parent.context.inflate(viewType, parent)
        return when (viewType) {
            OVERVIEW -> OverviewViewHolder(view)
            EPISODE -> EpisodeViewHolder(view)
            else -> throw IllegalArgumentException("Unknown viewtype")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is OverviewViewHolder -> holder.bind(item as SeasonItem.Overview)
            is EpisodeViewHolder -> holder.bind((item as SeasonItem.EpisodeItem).episode)
        }
    }

    fun setSeason(season: Season, details: SeasonDetails? = null) {
        val list = if (details != null) {
            listOf(SeasonItem.Overview(season, details)) +
                details.episodes.map { episode -> SeasonItem.EpisodeItem(episode) }
        } else {
            listOf(SeasonItem.Overview(season))
        }
        submitList(list)
    }

    companion object {
        private val OVERVIEW = R.layout.overview_viewholder
        private val EPISODE = R.layout.episode_viewholder
    }
}