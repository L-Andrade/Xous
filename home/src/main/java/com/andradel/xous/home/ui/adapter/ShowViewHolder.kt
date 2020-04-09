package com.andradel.xous.home.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.core.util.extensions.loadWithFade
import com.andradel.xous.home.R
import com.andradel.xous.home.ui.model.ShowItem

class ShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // TODO: Use LayoutContainer
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)

    fun bind(showItem: ShowItem.Item, goToShow: (Show) -> Unit) {
        itemView.setOnClickListener {
            goToShow(showItem.show)
        }
        imageView.loadWithFade(showItem.show.posterUrl)
    }
}