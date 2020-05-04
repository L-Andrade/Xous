package com.andradel.xous.showprofile.model

data class SeasonDetails(
    val episodes: List<Episode>,
    val episodeAverage: Float

)

data class Episode(
    val id: Int,
    val rating: Float
)