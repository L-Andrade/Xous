package com.andradel.xous.showprofile.ui.season.adapter

import com.andradel.xous.commonmodels.internal.Season
import com.andradel.xous.core.util.diffs.Item
import com.andradel.xous.showprofile.model.Episode
import com.andradel.xous.showprofile.model.SeasonDetails

sealed class SeasonItem(override val id: Int) : Item {
    data class Overview(val season: Season, val details: SeasonDetails? = null) :
        SeasonItem(season.id)

    data class EpisodeItem(val episode: Episode) : SeasonItem(episode.id)
}