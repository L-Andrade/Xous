package com.andradel.xous.commonmodels.internal.person

data class CrewMember(
    override val id: Int,
    override val name: String,
    override val profileUrl: String?,
    val job: String?,
    val department: String?,
    val isCreator: Boolean
) : Person