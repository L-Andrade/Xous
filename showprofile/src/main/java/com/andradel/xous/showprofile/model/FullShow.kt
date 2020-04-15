package com.andradel.xous.showprofile.model

import com.andradel.xous.common_models.internal.BaseShow
import com.andradel.xous.common_models.internal.GeneralShowsResponse

data class FullShow(
    override val id: Int,
    override val name: String,
    override val backdropUrl: String?,
    override val posterUrl: String?,
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
    val seasons: List<Season>,
    val backdrops: List<String>
) : BaseShow

data class Creator(
    val id: Int,
    val name: String,
    val profileUrl: String?
)

data class Season(
    val id: Int,
    val number: Int,
    val name: String,
    val posterUrl: String?,
    val numberOfEpisodes: Int,
    val overview: String
)