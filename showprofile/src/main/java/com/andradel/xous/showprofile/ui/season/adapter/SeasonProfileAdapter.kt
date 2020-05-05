package com.andradel.xous.showprofile.ui.season.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.commonmodels.internal.Season
import com.andradel.xous.core.util.diffs.ItemDiffUtils
import com.andradel.xous.core.util.extensions.inflate
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.model.SeasonDetails

class SeasonProfileAdapter(
    private val goToGallery: (String?) -> Unit
) : ListAdapter<SeasonItem, RecyclerView.ViewHolder>(ItemDiffUtils<SeasonItem>()) {

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is SeasonItem.Overview -> OVERVIEW
        is SeasonItem.EpisodeItem -> EPISODE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = parent.context.inflate(viewType, parent)
        return when (viewType) {
            OVERVIEW -> SeasonOverviewViewHolder(view)
            EPISODE -> EpisodeViewHolder(view)
            else -> throw IllegalArgumentException("Unknown viewtype")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is SeasonOverviewViewHolder -> holder.bind(item as SeasonItem.Overview, goToGallery)
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
        private val OVERVIEW = R.layout.season_overview_viewholder
        private val EPISODE = R.layout.episode_viewholder
    }
}