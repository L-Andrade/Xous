package com.andradel.xous.showprofile.ui.season.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.commonmodels.format
import com.andradel.xous.core.util.extensions.loadWithFade
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.model.Episode

class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val poster: ImageView = itemView.findViewById(R.id.poster)
    private val name: TextView = itemView.findViewById(R.id.name)
    private val overview: TextView = itemView.findViewById(R.id.overview)
    private val rating: TextView = itemView.findViewById(R.id.rating)

    fun bind(episode: Episode) {
        poster.loadWithFade(episode.stillUrl)
        name.text = episode.name
        overview.text = episode.overview
        rating.text = episode.rating.format()
    }
}