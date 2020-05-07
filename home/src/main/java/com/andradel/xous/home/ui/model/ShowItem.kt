package com.andradel.xous.home.ui.model

import com.andradel.xous.commonmodels.Item
import com.andradel.xous.commonmodels.internal.Show

sealed class ShowItem(override val id: String) :
    Item {

    data class Header(val title: String) : ShowItem(title)

    data class Item(val title: String, val show: Show) : ShowItem("$title${show.id}")

    object RecentlyViewedList : ShowItem("RECENTLY_VIEWED")
}