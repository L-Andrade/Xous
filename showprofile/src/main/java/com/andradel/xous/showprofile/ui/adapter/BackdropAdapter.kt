package com.andradel.xous.showprofile.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.andradel.xous.core.util.extensions.inflate
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.ui.adapter.viewholders.BackdropViewHolder

class BackdropAdapter(
    private val goToGallery: ((image: String) -> Unit)
) : ListAdapter<String, BackdropViewHolder>(ImageDiffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackdropViewHolder =
        BackdropViewHolder(parent.context.inflate(R.layout.backdrop_viewholder, parent))

    override fun onBindViewHolder(holder: BackdropViewHolder, position: Int) {
        holder.bind(getItem(position), goToGallery)
    }
}