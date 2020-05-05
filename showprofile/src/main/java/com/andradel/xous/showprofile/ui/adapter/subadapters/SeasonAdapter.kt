package com.andradel.xous.showprofile.ui.adapter.subadapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.andradel.xous.commonmodels.internal.Season
import com.andradel.xous.core.util.extensions.inflate
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.ui.adapter.viewholders.SeasonViewHolder

class SeasonAdapter(
    private val goToSeason: (Season) -> Unit
) : ListAdapter<Season, SeasonViewHolder>(SeasonDiffUtils) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder =
        SeasonViewHolder(parent.context.inflate(R.layout.season_viewholder, parent))

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        holder.bind(getItem(position), goToSeason)
    }
}