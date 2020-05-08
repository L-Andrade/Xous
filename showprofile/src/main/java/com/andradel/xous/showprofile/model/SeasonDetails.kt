package com.andradel.xous.showprofile.model

import com.andradel.xous.commonmodels.internal.person.CastMember
import com.andradel.xous.commonmodels.internal.person.CrewMember

data class SeasonDetails(
    val episodes: List<Episode>,
    val episodeAverage: Float
)

data class Episode(
    val id: Int,
    val name: String,
    val overview: String,
    val stillUrl: String?,
    val rating: Float,
    val number: Int,
    val guestStars: List<CastMember>,
    val crew: List<CrewMember>,
    var expanded: Boolean = false
) {
    fun invertExpanded(): Episode = this.copy(expanded = !expanded)
}