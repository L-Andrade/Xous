package com.andradel.xous.showprofile.ui.adapter.viewholders

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.commonmodels.format
import com.andradel.xous.commonmodels.internal.BaseShow
import com.andradel.xous.commonmodels.internal.Season
import com.andradel.xous.commonui.views.ExpandingTextView
import com.andradel.xous.core.util.extensions.getHtmlSpannedString
import com.andradel.xous.core.util.extensions.getString
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.model.FullShow
import com.andradel.xous.showprofile.ui.season.adapter.SeasonItem

class OverviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val overview: ExpandingTextView = itemView.findViewById(R.id.overview)
    private val rating: TextView = itemView.findViewById(R.id.rating)
    private val numberOfEpisodes: TextView = itemView.findViewById(R.id.numberOfEpisodes)
    private val numberOfSeasons: TextView = itemView.findViewById(R.id.numberOfSeasons)
    private val firstAired: TextView = itemView.findViewById(R.id.firstAired)

    fun bind(show: BaseShow) {
        val c = itemView.context
        rating.text = show.rating.format()
        firstAired.text = c.getHtmlSpannedString(R.string.first_aired, show.firstAired)
        overview.text = if (show.overview.isNotEmpty())
            show.overview else itemView.getString(R.string.empty_overview)

        if (show is FullShow) {
            numberOfSeasons.text =
                c.getHtmlSpannedString(R.string.number_of_seasons, show.numberOfSeasons)
            numberOfEpisodes.text =
                c.getHtmlSpannedString(R.string.number_of_episodes, show.numberOfEpisodes)
        }
    }

    fun bind(item: SeasonItem.Overview) {
        val season = item.season
        val details = item.details
        bind(season)
        if (details != null) {
            rating.text = details.episodeAverage.format()
        }
    }

    private fun bind(season: Season) {
        val c = itemView.context
        firstAired.text = c.getHtmlSpannedString(R.string.first_aired, season.firstAired)
        overview.text = if (season.overview.isNotEmpty())
            season.overview else itemView.getString(R.string.empty_overview)

        numberOfSeasons.isVisible = false
        numberOfEpisodes.text =
            c.getHtmlSpannedString(R.string.number_of_episodes, season.numberOfEpisodes)
    }
}