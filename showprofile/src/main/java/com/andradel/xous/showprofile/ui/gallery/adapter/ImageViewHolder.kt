package com.andradel.xous.showprofile.ui.gallery.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.core.util.extensions.loadWithFade
import com.andradel.xous.showprofile.R

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imageView: ImageView = itemView.findViewById(R.id.photoView)

    fun bind(image: String, askToSaveImage: (String) -> Unit) {
        imageView.loadWithFade(image)
        imageView.setOnLongClickListener {
            askToSaveImage(image)
            false
        }
    }
}