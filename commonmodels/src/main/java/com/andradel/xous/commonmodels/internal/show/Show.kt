package com.andradel.xous.commonmodels.internal.show

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

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