package com.andradel.xous.showprofile.ui.adapter.viewholders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.commonui.extensions.load
import com.andradel.xous.showprofile.R

class BackdropViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val backdrop: ImageView = itemView.findViewById(R.id.backdrop)

    fun bind(imageUrl: String, goToGallery: (image: String) -> Unit) {
        backdrop.load(imageUrl)

        backdrop.setOnClickListener { goToGallery(imageUrl) }
    }
}