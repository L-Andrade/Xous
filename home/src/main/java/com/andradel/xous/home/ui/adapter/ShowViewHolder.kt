package com.andradel.xous.home.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.commonmodels.internal.Show
import com.andradel.xous.core.util.extensions.loadWithFade
import com.andradel.xous.home.R

class ShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // TODO: Use LayoutContainer
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)

    fun bind(show: Show, goToShow: (Show) -> Unit) {
        itemView.setOnClickListener {
            goToShow(show)
        }
        imageView.loadWithFade(show.posterUrl)
    }
}