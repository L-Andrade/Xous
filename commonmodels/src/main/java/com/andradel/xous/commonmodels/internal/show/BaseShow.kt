package com.andradel.xous.commonmodels.internal.show

import com.andradel.xous.commonmodels.Item

interface BaseShow : Item {
    val name: String
    val posterUrl: String?
    val backdropUrl: String?
    val overview: String
    val firstAired: String
    val rating: Float
}