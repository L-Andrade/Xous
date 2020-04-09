package com.andradel.xous.showprofile.model

import com.andradel.xous.common_models.internal.BaseShow
import com.andradel.xous.common_models.toImagePath

data class FullShow(
    override val id: Long,
    override val name: String,
    override val backdropPath: String?,
    override val posterPath: String?,
    override val overview: String,
    override val rating: Float,
    override val firstAired: String,
    val createdBy: List<Creator>,
    val lastAired: String,
    val inProduction: Boolean,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val status: String,
    val type: String
) : BaseShow

data class Creator(
    val id: Long,
    val name: String,
    private val profilePath: String?
) {
    val profileUrl: String?
        get() = profilePath.toImagePath(com.andradel.xous.common_models.ImageSize.Profile.Small)
}