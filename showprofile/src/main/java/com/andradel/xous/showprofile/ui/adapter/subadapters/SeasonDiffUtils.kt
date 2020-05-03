package com.andradel.xous.showprofile.ui.adapter.subadapters

import androidx.recyclerview.widget.DiffUtil
import com.andradel.xous.common_models.internal.Season


object SeasonDiffUtils : DiffUtil.ItemCallback<Season>() {
    override fun areItemsTheSame(oldItem: Season, newItem: Season): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Season, newItem: Season): Boolean =
        oldItem == newItem
}