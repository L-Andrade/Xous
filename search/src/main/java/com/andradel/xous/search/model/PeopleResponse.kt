package com.andradel.xous.search.model

import com.andradel.xous.commonmodels.internal.person.RegularPerson

data class PeopleResponse(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val results: List<RegularPerson>
)