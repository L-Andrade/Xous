package com.andradel.xous.showprofile.ui.gallery.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.andradel.xous.core.util.extensions.inflate
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.ui.adapter.ImageDiffUtils

class ImageAdapter(
    private val askToSaveImage: (String) -> Unit
) : ListAdapter<String, ImageViewHolder>(ImageDiffUtils) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(parent.context.inflate(R.layout.image_viewholder, parent))

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position), askToSaveImage)
    }
}