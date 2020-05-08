package com.andradel.xous.commonmodels.internal.person

import com.andradel.xous.commonmodels.Item

interface Person : Item {
    override val id: Int
    val name: String
    val profileUrl: String?
}