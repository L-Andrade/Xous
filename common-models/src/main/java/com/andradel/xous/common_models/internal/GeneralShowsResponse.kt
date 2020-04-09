package com.andradel.xous.common_models.internal

import android.os.Parcelable
import com.andradel.xous.common_models.ImageSize
import com.andradel.xous.common_models.toImagePath
import kotlinx.android.parcel.Parcelize

data class GeneralShowsResponse(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val items: List<Show>
)

@Parcelize
data class Show(
    override val id: Long,
    override val name: String,
    override val posterPath: String?,
    override val backdropPath: String?,
    override val overview: String,
    override val firstAired: String,
    override val rating: Float
) : BaseShow, Parcelable

interface BaseShow {
    val id: Long
    val name: String
    val posterPath: String?
    val backdropPath: String?
    val overview: String
    val firstAired: String
    val rating: Float

    val posterUrl: String?
        get() = posterPath.toImagePath(ImageSize.Poster.Medium)

    val backdropUrl: String?
        get() = backdropPath.toImagePath(ImageSize.Backdrop.Medium)
}