package com.andradel.xous.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.home.R

class RecentlyViewedAdapter(
    private val goToShow: (Show) -> Unit
) : ListAdapter<Show, ShowViewHolder>(diffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder =
        ShowViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.show_viewholder, parent, false)
        )

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(getItem(position), goToShow)
    }

    companion object {
        private val diffUtils =
            object : DiffUtil.ItemCallback<Show>() {
                override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean =
                    oldItem == newItem
            }
    }
}