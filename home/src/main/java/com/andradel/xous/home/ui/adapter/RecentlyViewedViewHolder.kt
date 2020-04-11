package com.andradel.xous.home.ui.adapter

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.home.R

class RecentlyViewedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val recyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView)

    fun bind(recentlyViewedAdapter: RecentlyViewedAdapter) {
        recyclerView.apply {
            adapter = recentlyViewedAdapter
            layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
        }
    }
}