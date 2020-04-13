package com.andradel.xous.showprofile.ui.adapter.subadapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.core.util.extensions.inflate
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.ui.adapter.viewholders.ShowViewHolder

class SimilarShowAdapter(
    private val goToShow: (Show) -> Unit
) : ListAdapter<Show, ShowViewHolder>(diffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder =
        ShowViewHolder(
            parent.context.inflate(R.layout.similar_show_viewholder, parent)
        )

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(getItem(position), goToShow)
    }

    companion object {
        private val diffUtils = object : DiffUtil.ItemCallback<Show>() {
            override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean =
                newItem == oldItem
        }
    }
}