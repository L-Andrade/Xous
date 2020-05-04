package com.andradel.xous.home.ui.model

import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.core.util.diffs.Item

sealed class ShowItem(override val id: String) : Item {

    data class Header(val title: String) : ShowItem(title)

    data class Item(val title: String, val show: Show) : ShowItem("$title${show.id}")

    object RecentlyViewedList : ShowItem("RECENTLY_VIEWED")
}