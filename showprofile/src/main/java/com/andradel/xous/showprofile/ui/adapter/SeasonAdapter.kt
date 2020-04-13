package com.andradel.xous.showprofile.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.andradel.xous.core.util.extensions.inflate
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.model.Season

class SeasonAdapter() : ListAdapter<Season, SeasonViewHolder>(diffUtils) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder =
        SeasonViewHolder(parent.context.inflate(R.layout.season_viewholder, parent))

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffUtils = object : DiffUtil.ItemCallback<Season>() {
            override fun areItemsTheSame(oldItem: Season, newItem: Season): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Season, newItem: Season): Boolean =
                oldItem == newItem
        }
    }
}