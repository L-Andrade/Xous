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

    sealed class Content(title: String, val adapter: ListAdapter<*, *>) : ProfileItem(title) {
        abstract val title: String

        data class SimilarShows(
            override val title: String,
            val shows: List<Show>,
            val goToShow: (Show) -> Unit
        ) : Content(title, SimilarShowAdapter(goToShow).also { it.submitList(shows) })

        data class People<T : Person>(override val title: String, val people: List<T>) :
            Content(title, PersonAdapter().also { it.submitList(people) })

        data class Seasons(
            override val title: String,
            val seasons: List<Season>,
            val goToSeason: (Season) -> Unit
        ) : Content(title, SeasonAdapter(goToSeason).also { it.submitList(seasons) })
    }
}