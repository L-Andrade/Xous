package com.andradel.xous.showprofile.ui.adapter

import androidx.annotation.StringRes
import com.andradel.xous.commonmodels.Item
import com.andradel.xous.commonmodels.internal.Season
import com.andradel.xous.commonmodels.internal.person.Person
import com.andradel.xous.commonmodels.internal.show.BaseShow
import com.andradel.xous.commonmodels.internal.show.Show

sealed class ProfileItem(override val id: Int) : Item {
    data class Overview(val show: BaseShow) : ProfileItem(show.id as Int)

    // TODO: This can probably be improved. Dislike the wildcard here!
    sealed class Content(title: Int) : ProfileItem(title) {
        abstract val title: Int
        abstract val list: List<*>

        data class SimilarShows(@StringRes override val title: Int, override val list: List<Show>) :
            Content(title)

        data class People<T : Person>(@StringRes override val title: Int, override val list: List<T>) :
            Content(title)

        data class Seasons(@StringRes override val title: Int, override val list: List<Season>) :
            Content(title)
    }
}