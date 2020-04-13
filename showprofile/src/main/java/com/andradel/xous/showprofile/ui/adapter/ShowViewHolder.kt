package com.andradel.xous.showprofile.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.core.util.extensions.loadWithFade
import com.andradel.xous.showprofile.R

class ShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val poster: ImageView = itemView.findViewById(R.id.poster)

    fun bind(show: Show, onClick: (Show) -> Unit) {
        poster.setOnClickListener {
            onClick(show)
        }
        poster.loadWithFade(show.posterUrl)
    }
}