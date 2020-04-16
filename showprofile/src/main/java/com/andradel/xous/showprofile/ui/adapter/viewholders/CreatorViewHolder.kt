package com.andradel.xous.showprofile.ui.adapter.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.core.util.extensions.loadCircleWithFade
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.model.Creator

class CreatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val creatorName: TextView = itemView.findViewById(R.id.creatorName)
    private val creatorImage: ImageView = itemView.findViewById(R.id.creatorImage)

    fun bind(creator: Creator) {
        creatorName.text = creator.name
        creatorImage.loadCircleWithFade(creator.profileUrl, R.drawable.ic_person_black_24dp)
    }
}