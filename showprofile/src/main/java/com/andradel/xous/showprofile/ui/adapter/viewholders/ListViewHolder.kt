package com.andradel.xous.showprofile.ui.adapter.viewholders

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.commonmodels.internal.Season
import com.andradel.xous.commonmodels.internal.person.Person
import com.andradel.xous.commonmodels.internal.show.Show
import com.andradel.xous.commonui.extensions.getString
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.ui.adapter.ProfileItem
import com.andradel.xous.showprofile.ui.adapter.subadapters.PersonAdapter
import com.andradel.xous.showprofile.ui.adapter.subadapters.SeasonAdapter
import com.andradel.xous.showprofile.ui.adapter.subadapters.SimilarShowAdapter

class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val recyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView)
    private val headerTitle: TextView = itemView.findViewById(R.id.headerTitle)

    // TODO: This still has some ways to go, WIP!
    fun bind(item: ProfileItem.Content.SimilarShows, goToShow: (Show) -> Unit) {
        recyclerView.adapter = SimilarShowAdapter(goToShow).also {
            it.submitList(item.list)
            // it.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
        bindCommon(item)
    }

    fun bind(item: ProfileItem.Content.People<*>, goToPerson: (Person) -> Unit) {
        recyclerView.adapter = PersonAdapter().also {
            it.submitList(item.list)
            // it.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
        bindCommon(item)
    }

    fun bind(item: ProfileItem.Content.Seasons, goToSeason: (Season) -> Unit) {
        recyclerView.adapter = SeasonAdapter(goToSeason).also {
            it.submitList(item.list)
            // it.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
        bindCommon(item)
    }

    private fun bindCommon(item: ProfileItem.Content) {
        headerTitle.isVisible = item.list.isNotEmpty()
        headerTitle.text = itemView.getString(item.title)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }
}