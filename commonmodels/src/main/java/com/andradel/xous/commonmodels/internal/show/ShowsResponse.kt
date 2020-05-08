package com.andradel.xous.commonmodels.internal.show

data class ShowsResponse(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val items: List<Show>
)