package com.andradel.xous.commonmodels.internal.person

import com.andradel.xous.commonmodels.internal.show.Show

data class RegularPerson(
    override val id: Int,
    override val name: String,
    override val profileUrl: String?,
    val department: String,
    val knownFor: List<Show>
) : Person