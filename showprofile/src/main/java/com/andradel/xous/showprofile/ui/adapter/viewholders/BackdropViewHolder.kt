package com.andradel.xous.showprofile.ui.adapter.viewholders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.core.util.extensions.loadWithFade
import com.andradel.xous.showprofile.R

class BackdropViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val backdrop: ImageView = itemView.findViewById(R.id.backdrop)

    fun bind(imageUrl: String) {
        backdrop.loadWithFade(imageUrl)
    }
}