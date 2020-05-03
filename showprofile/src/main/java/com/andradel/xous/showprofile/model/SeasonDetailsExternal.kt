package com.andradel.xous.showprofile.model

import com.andradel.xous.common_models.orZero

data class SeasonDetailsExternal(
    val episodes: List<EpisodeExternal>?
) {
    fun toInternal(): SeasonDetails = SeasonDetails(
        episodes = episodes.orEmpty().map { it.toInternal() }
    )
}

data class EpisodeExternal(
    val id: Int?
) {
    fun toInternal(): Episode = Episode(
        id = id.orZero()
    )
}