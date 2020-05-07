package com.andradel.xous.showprofile.ui.adapter.subadapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.andradel.xous.commonui.diffs.ItemDiffUtils
import com.andradel.xous.core.util.extensions.inflate
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.model.Person
import com.andradel.xous.showprofile.ui.adapter.viewholders.PersonViewHolder

class PersonAdapter : ListAdapter<Person, PersonViewHolder>(ItemDiffUtils<Person>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder =
        PersonViewHolder(parent.context.inflate(R.layout.person_viewholder, parent))

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}