package com.andradel.xous.showprofile.ui.adapter.viewholders

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.showprofile.R

class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val recyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView)
    private val headerTitle: TextView = itemView.findViewById(R.id.headerTitle)

    fun bind(title: String, rvAdapter: ListAdapter<*, *>) {
        headerTitle.isVisible = rvAdapter.itemCount > 0
        headerTitle.text = title
        recyclerView.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }
}