package com.andradel.xous.showprofile.ui.adapter

import androidx.recyclerview.widget.DiffUtil

object ImageDiffUtils : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem
}