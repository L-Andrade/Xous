package com.andradel.xous.showprofile.ui.adapter

import androidx.recyclerview.widget.ListAdapter
import com.andradel.xous.common_models.internal.BaseShow
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.showprofile.model.Person
import com.andradel.xous.showprofile.model.Season
import com.andradel.xous.showprofile.ui.adapter.subadapters.PersonAdapter
import com.andradel.xous.showprofile.ui.adapter.subadapters.SeasonAdapter
import com.andradel.xous.showprofile.ui.adapter.subadapters.SimilarShowAdapter

sealed class ProfileItem {
    data class Overview(val show: BaseShow) : ProfileItem()

    sealed class Content(val title: String, val adapter: ListAdapter<*, *>) : ProfileItem() {
        class SimilarShows(title: String, shows: List<Show>, goToShow: (Show) -> Unit) :
            Content(title, SimilarShowAdapter(goToShow).also { it.submitList(shows) })

        class People<T : Person>(title: String, people: List<T>) :
            Content(title, PersonAdapter().also { it.submitList(people) })

        class Seasons(title: String, seasons: List<Season>) :
            Content(title, SeasonAdapter().also { it.submitList(seasons) })
    }
}