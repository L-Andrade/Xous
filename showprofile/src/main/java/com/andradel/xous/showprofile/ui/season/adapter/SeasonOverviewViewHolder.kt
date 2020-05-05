package com.andradel.xous.showprofile.ui.season.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.commonmodels.format
import com.andradel.xous.commonmodels.internal.Season
import com.andradel.xous.commonui.views.ExpandingTextView
import com.andradel.xous.core.util.extensions.getHtmlSpannedString
import com.andradel.xous.core.util.extensions.getString
import com.andradel.xous.core.util.extensions.loadWithFade
import com.andradel.xous.showprofile.R

class SeasonOverviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val overview: ExpandingTextView = itemView.findViewById(R.id.overview)
    private val poster: ImageView = itemView.findViewById(R.id.poster)
    private val rating: TextView = itemView.findViewById(R.id.rating)
    private val numberOfEpisodes: TextView = itemView.findViewById(R.id.numberOfEpisodes)
    private val firstAired: TextView = itemView.findViewById(R.id.firstAired)

    fun bind(item: SeasonItem.Overview, goToGallery: (String?) -> Unit) {
        val season = item.season
        val details = item.details
        bind(season, goToGallery)
        if (details != null) {
            rating.isVisible = true
            rating.text = itemView.context.getHtmlSpannedString(
                R.string.episode_average,
                details.episodeAverage.format()
            )
        }
    }

    private fun bind(season: Season, goToGallery: (String?) -> Unit) {
        val c = itemView.context
        poster.loadWithFade(season.posterUrl)
        poster.setOnClickListener { goToGallery(season.posterUrl) }
        firstAired.text = c.getHtmlSpannedString(R.string.first_aired, season.firstAired)
        overview.text = if (season.overview.isNotEmpty())
            season.overview else itemView.getString(R.string.empty_overview)
        numberOfEpisodes.text =
            c.getHtmlSpannedString(R.string.number_of_episodes, season.numberOfEpisodes)
    }
}