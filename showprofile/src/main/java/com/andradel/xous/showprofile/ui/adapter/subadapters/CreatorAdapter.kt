package com.andradel.xous.showprofile.ui.adapter.subadapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.andradel.xous.core.util.extensions.inflate
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.model.Creator
import com.andradel.xous.showprofile.ui.adapter.viewholders.CreatorViewHolder

class CreatorAdapter : ListAdapter<Creator, CreatorViewHolder>(diffUtils) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorViewHolder =
        CreatorViewHolder(
            parent.context.inflate(R.layout.creator_viewholder, parent)
        )

    override fun onBindViewHolder(holder: CreatorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffUtils = object : DiffUtil.ItemCallback<Creator>() {
            override fun areItemsTheSame(oldItem: Creator, newItem: Creator): Boolean =
                newItem.id == oldItem.id

            override fun areContentsTheSame(oldItem: Creator, newItem: Creator): Boolean =
                newItem == oldItem
        }
    }
}