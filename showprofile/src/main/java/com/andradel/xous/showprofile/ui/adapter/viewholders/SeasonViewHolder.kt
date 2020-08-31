package com.andradel.xous.showprofile.ui.adapter.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.commonmodels.internal.Season
import com.andradel.xous.commonui.extensions.load
import com.andradel.xous.showprofile.R

class SeasonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val seasonName: TextView = itemView.findViewById(R.id.seasonName)
    private val seasonPoster: ImageView = itemView.findViewById(R.id.seasonPoster)

    fun bind(season: Season, goToSeason: (Season) -> Unit) {
        seasonPoster.setOnClickListener {
            goToSeason(season)
        }
        seasonName.text = season.name
        seasonPoster.load(season.posterUrl)
    }
}