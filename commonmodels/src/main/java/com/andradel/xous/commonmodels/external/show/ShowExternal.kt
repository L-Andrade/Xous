package com.andradel.xous.commonmodels.external.show

import com.andradel.xous.commonmodels.ImageSize
import com.andradel.xous.commonmodels.internal.show.Show
import com.andradel.xous.commonmodels.orZero
import com.andradel.xous.commonmodels.toImagePath
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShowExternal(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("vote_average") val rating: Float? = null,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("first_air_date") val firstAired: String? = null,
    @SerialName("overview") val overview: String? = null
) {
    fun toInternal(): Show {
        return Show(
            id = id.orZero(),
            name = name.orEmpty(),
            posterUrl = posterPath.toImagePath(ImageSize.Poster.Medium),
            rating = rating.orZero(),
            backdropUrl = backdropPath.toImagePath(ImageSize.Backdrop.Medium),
            firstAired = firstAired.orEmpty(),
            overview = overview.orEmpty()
        )
    }
}