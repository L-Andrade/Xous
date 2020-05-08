package com.andradel.xous.commonmodels.internal.person

data class CastMember(
    override val id: Int,
    override val name: String,
    override val profileUrl: String?,
    val character: String
) : Person