package com.andradel.xous.search.ui

import com.andradel.xous.commonmodels.Item
import com.andradel.xous.commonmodels.internal.person.RegularPerson
import com.andradel.xous.commonmodels.internal.show.Show

sealed class SearchItem(override val id: Int) : Item {
    data class ShowItem(val item: Show) : SearchItem(item.id)
    data class PersonItem(val item: RegularPerson) : SearchItem(item.id)
}