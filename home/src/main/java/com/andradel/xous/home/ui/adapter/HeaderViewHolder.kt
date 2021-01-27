package com.andradel.xous.home.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.home.R
import com.andradel.xous.home.ui.model.ShowItem

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // TODO LayoutContainer
    private val textView: TextView = itemView.findViewById(R.id.title)
    fun bind(header: ShowItem.Header) {
        textView.text = itemView.context.getString(header.title)
    }
}