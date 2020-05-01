package com.andradel.xous.showprofile.ui.adapter.subadapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.andradel.xous.core.util.extensions.inflate
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.model.Person
import com.andradel.xous.showprofile.ui.adapter.viewholders.PersonViewHolder

class PersonAdapter : ListAdapter<Person, PersonViewHolder>(diffUtils) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder =
        PersonViewHolder(parent.context.inflate(R.layout.person_viewholder, parent))

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffUtils = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean =
                newItem.id == oldItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean =
                newItem == oldItem
        }
    }
}