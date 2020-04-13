package com.andradel.xous.showprofile.ui.adapter.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.showprofile.R

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val header: TextView = itemView.findViewById(R.id.headerTitle)

    fun bind(title: String) {
        header.text = title
    }
}