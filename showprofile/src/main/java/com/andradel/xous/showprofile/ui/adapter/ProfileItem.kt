package com.andradel.xous.showprofile.ui.adapter

import androidx.recyclerview.widget.ListAdapter
import com.andradel.xous.common_models.internal.BaseShow
import com.andradel.xous.common_models.internal.Season
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.core.util.diffs.Item
import com.andradel.xous.showprofile.model.Person
import com.andradel.xous.showprofile.ui.adapter.subadapters.PersonAdapter
import com.andradel.xous.showprofile.ui.adapter.subadapters.SeasonAdapter
import com.andradel.xous.showprofile.ui.adapter.subadapters.SimilarShowAdapter

sealed class ProfileItem(override val id: String) : Item {
    data class Overview(val show: BaseShow) : ProfileItem(show.id.toString())

    sealed class Content(val title: String, val adapter: ListAdapter<*, *>) : ProfileItem(title) {
        class SimilarShows(title: String, shows: List<Show>, goToShow: (Show) -> Unit) :
            Content(title, SimilarShowAdapter(goToShow).also { it.submitList(shows) })

        class People<T : Person>(title: String, people: List<T>) :
            Content(title, PersonAdapter().also { it.submitList(people) })

        class Seasons(title: String, seasons: List<Season>, goToSeason: (Season) -> Unit) :
            Content(title, SeasonAdapter(goToSeason).also { it.submitList(seasons) })
    }
}