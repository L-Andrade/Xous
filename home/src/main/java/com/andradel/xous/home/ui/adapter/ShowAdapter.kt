package com.andradel.xous.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.home.R
import com.andradel.xous.home.ui.model.ShowItem

class ShowAdapter(
    private val goToShow: (Show) -> Unit
) : ListAdapter<ShowItem, RecyclerView.ViewHolder>(diffUtils) {

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is ShowItem.Header -> HEADER
        else -> SHOW
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            HEADER ->
                HeaderViewHolder(layoutInflater.inflate(R.layout.header_viewholder, parent, false))
            SHOW ->
                ShowViewHolder(layoutInflater.inflate(R.layout.show_viewholder, parent, false))
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is HeaderViewHolder -> holder.bind(item as ShowItem.Header)
            is ShowViewHolder -> holder.bind(item as ShowItem.Item, goToShow)
        }
    }

    companion object {
        const val HEADER = 1
        private const val SHOW = 2
        private val diffUtils =
            object : DiffUtil.ItemCallback<ShowItem>() {
                override fun areItemsTheSame(oldItem: ShowItem, newItem: ShowItem): Boolean =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: ShowItem, newItem: ShowItem): Boolean =
                    oldItem == newItem
            }
    }
}