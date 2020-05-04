package com.andradel.xous.showprofile.model

import com.andradel.xous.common_models.ImageSize
import com.andradel.xous.common_models.orZero
import com.andradel.xous.common_models.toImagePath
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonDetailsExternal(
    @SerialName("episodes") val episodes: List<EpisodeExternal>?
) {
    fun toInternal(): SeasonDetails = SeasonDetails(
        episodes = episodes.orEmpty().map { it.toInternal() },
        episodeAverage = episodes.orEmpty().mapNotNull { it.rating }.average().toFloat()
    )
}

@Serializable
data class EpisodeExternal(
    @SerialName("id") val id: Int?,
    @SerialName("vote_average") val rating: Float?,
    @SerialName("name") val name: String?,
    @SerialName("still_path") val stillPath: String?,
    @SerialName("overview") val overview: String?
) {
    fun toInternal(): Episode = Episode(
        id = id.orZero(),
        name = name.orEmpty(),
        overview = overview.orEmpty(),
        stillUrl = stillPath.toImagePath(ImageSize.Still.Big),
        rating = rating.orZero()
    )
}