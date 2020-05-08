package com.andradel.xous.commonmodels.internal.show

data class GeneralShowsResponse(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val items: List<Show>
)