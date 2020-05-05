package com.andradel.xous.commonmodels.internal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class GeneralShowsResponse(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val items: List<Show>
)

@Parcelize
data class Show(
    override val id: Int,
    override val name: String,
    override val posterUrl: String?,
    override val backdropUrl: String?,
    override val overview: String,
    override val firstAired: String,
    override val rating: Float
) : BaseShow, Parcelable

interface BaseShow {
    val id: Int
    val name: String
    val posterUrl: String?
    val backdropUrl: String?
    val overview: String
    val firstAired: String
    val rating: Float
}