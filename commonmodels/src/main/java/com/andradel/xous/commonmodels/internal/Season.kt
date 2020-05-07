package com.andradel.xous.commonmodels.internal

import android.os.Parcelable
import com.andradel.xous.commonmodels.Item
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Season(
    override val id: Int,
    val number: Int,
    val name: String,
    val posterUrl: String?,
    val numberOfEpisodes: Int,
    val overview: String,
    val firstAired: String
) : Parcelable, Item