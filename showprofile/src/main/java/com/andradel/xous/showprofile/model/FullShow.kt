package com.andradel.xous.showprofile.model

import com.andradel.xous.commonmodels.internal.BaseShow
import com.andradel.xous.commonmodels.internal.GeneralShowsResponse
import com.andradel.xous.commonmodels.internal.Season
import com.andradel.xous.core.util.diffs.Item

data class FullShow(
    override val id: Int,
    override val name: String,
    override val backdropUrl: String?,
    override val posterUrl: String?,
    override val overview: String,
    override val rating: Float,
    override val firstAired: String,
    val crew: List<CrewMember>,
    val cast: List<CastMember>,
    val lastAired: String,
    val inProduction: Boolean,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val status: String,
    val type: String,
    val similarShows: GeneralShowsResponse,
    val seasons: List<Season>,
    val backdrops: List<String>,
    val allImages: List<String>
) : BaseShow

data class CrewMember(
    override val id: Int,
    override val name: String,
    override val profileUrl: String?,
    val job: String?,
    val isCreator: Boolean
) : Person

data class CastMember(
    override val id: Int,
    override val name: String,
    override val profileUrl: String?,
    val character: String
) : Person

interface Person : Item {
    override val id: Int
    val name: String
    val profileUrl: String?
}