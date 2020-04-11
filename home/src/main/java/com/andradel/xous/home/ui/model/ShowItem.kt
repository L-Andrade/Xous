package com.andradel.xous.home.ui.model

import com.andradel.xous.common_models.internal.Show

sealed class ShowItem {
    abstract val id: String

    data class Header(val title: String) : ShowItem() {
        override val id = title
    }

    data class Item(val title: String, val show: Show) : ShowItem() {
        // Because there might be shows in two different categories
        override val id = "$title${show.id}"
    }

    object RecentlyViewedList : ShowItem() {
        override val id: String = "RECENTLY_VIEWED"
    }
}