package com.andradel.xous.commonui.viewholders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.commonmodels.internal.Show
import com.andradel.xous.commonui.R
import com.andradel.xous.commonui.extensions.loadWithFade

class ShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val poster: ImageView = itemView.findViewById(R.id.imageView)

    fun bind(show: Show, onClick: (Show) -> Unit) {
        poster.setOnClickListener {
            onClick(show)
        }
        poster.loadWithFade(show.posterUrl)
    }
}