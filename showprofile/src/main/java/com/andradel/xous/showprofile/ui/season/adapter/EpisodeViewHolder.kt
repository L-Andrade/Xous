package com.andradel.xous.showprofile.ui.season.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager.beginDelayedTransition
import com.andradel.xous.commonmodels.format
import com.andradel.xous.commonui.extensions.load
import com.andradel.xous.core.util.extensions.getHtmlSpannedString
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.model.Episode
import com.andradel.xous.showprofile.ui.adapter.subadapters.PersonAdapter

class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val parentView: ViewGroup = itemView.findViewById(R.id.parentView)
    private val poster: ImageView = itemView.findViewById(R.id.poster)
    private val name: TextView = itemView.findViewById(R.id.name)
    private val overview: TextView = itemView.findViewById(R.id.overview)
    private val rating: TextView = itemView.findViewById(R.id.rating)
    private val guestStars: RecyclerView = itemView.findViewById(R.id.guestStars)
    private val crew: RecyclerView = itemView.findViewById(R.id.crew)
    private val extraInfoContainer: ViewGroup = itemView.findViewById(R.id.extraInfoContainer)
    private val guestStarsHeader: TextView = itemView.findViewById(R.id.guestStarsHeader)
    private val crewHeader: TextView = itemView.findViewById(R.id.crewHeader)

    private val crewAdapter = PersonAdapter()
    private val castAdapter = PersonAdapter()

    fun bind(episode: Episode, onClick: (Episode) -> Unit) {
        poster.load(episode.stillUrl)
        name.text = itemView.context.getHtmlSpannedString(
            R.string.episode_with_number,
            episode.name,
            episode.number
        )
        overview.text = episode.overview
        rating.text = episode.rating.format()

        poster.setOnClickListener {
            onClick(episode)
        }

        expandedState(episode.expanded)

        guestStarsHeader.isVisible = episode.guestStars.isNotEmpty()
        guestStars.apply {
            adapter = castAdapter.also { it.submitList(episode.guestStars) }
            layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        crewHeader.isVisible = episode.crew.isNotEmpty()
        crew.apply {
            adapter = crewAdapter.also { it.submitList(episode.crew) }
            layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    fun payloadBind(episode: Episode) {
        expandedState(episode.expanded)
    }

    private fun expandedState(expanded: Boolean) {
        extraInfoContainer.isVisible = expanded
        beginDelayedTransition(parentView)
        overview.maxLines = if (expanded)
            Int.MAX_VALUE
        else
            itemView.resources.getInteger(R.integer.episode_max_lines)
    }
}