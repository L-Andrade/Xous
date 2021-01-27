package com.andradel.xous.home.ui.model

import androidx.annotation.StringRes
import com.andradel.xous.commonmodels.Item
import com.andradel.xous.commonmodels.internal.show.Show

sealed class ShowItem(override val id: Any) : Item {

    data class Header(@StringRes val title: Int) : ShowItem(title)

    data class Item(@StringRes val title: Int, val show: Show) : ShowItem(title + show.id)

    object RecentlyViewedList : ShowItem("RECENTLY_VIEWED")
}