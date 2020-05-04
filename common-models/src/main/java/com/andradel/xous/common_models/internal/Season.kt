package com.andradel.xous.common_models.internal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Season(
    val id: Int,
    val number: Int,
    val name: String,
    val posterUrl: String?,
    val numberOfEpisodes: Int,
    val overview: String,
    val firstAired: String
) : Parcelable