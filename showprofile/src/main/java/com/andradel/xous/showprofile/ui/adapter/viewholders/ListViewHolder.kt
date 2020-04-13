package com.andradel.xous.showprofile.ui.adapter.viewholders

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.showprofile.R

class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val recyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView)

    fun bind(rvAdapter: ListAdapter<*, *>) {
        recyclerView.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }
}