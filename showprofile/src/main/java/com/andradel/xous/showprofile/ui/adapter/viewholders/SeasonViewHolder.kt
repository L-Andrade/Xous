package com.andradel.xous.showprofile.ui.adapter.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.core.util.extensions.loadWithFade
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.model.Season

class SeasonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val seasonName: TextView = itemView.findViewById(R.id.seasonName)
    private val seasonPoster: ImageView = itemView.findViewById(R.id.seasonPoster)

    fun bind(season: Season) {
        seasonName.text = season.name
        seasonPoster.loadWithFade(season.posterUrl)
    }
}