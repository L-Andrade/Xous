package com.andradel.xous.showprofile.model

import com.andradel.xous.common_models.ImageSize
import com.andradel.xous.common_models.internal.BaseShow
import com.andradel.xous.common_models.internal.GeneralShowsResponse
import com.andradel.xous.common_models.toImagePath

data class FullShow(
    override val id: Int,
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
    val type: String,
    val similarShows: GeneralShowsResponse,
    val seasons: List<Season>
) : BaseShow

data class Creator(
    val id: Int,
    val name: String,
    private val profilePath: String?
) {
    val profileUrl: String?
        get() = profilePath.toImagePath(ImageSize.Profile.Medium)
}

data class Season(
    val id: Int,
    val number: Int,
    val name: String,
    private val posterPath: String,
    val numberOfEpisodes: Int,
    val overview: String
) {
    val posterUrl: String?
        get() = posterPath.toImagePath(ImageSize.Poster.Medium)
}