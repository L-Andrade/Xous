package com.andradel.xous.showprofile.ui.season.adapter

import com.andradel.xous.commonmodels.internal.Season
import com.andradel.xous.core.util.diffs.Item
import com.andradel.xous.showprofile.model.Episode

sealed class SeasonItem(override val id: Int) : Item {
    data class Overview(val season: Season, val episodeAverage: Float? = null) :
        SeasonItem(season.id)

    data class EpisodeItem(val episode: Episode) : SeasonItem(episode.id)
}