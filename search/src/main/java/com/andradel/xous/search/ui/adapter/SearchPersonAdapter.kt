package com.andradel.xous.search.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.andradel.xous.commonmodels.internal.person.Person
import com.andradel.xous.commonui.diffs.ItemDiffUtils
import com.andradel.xous.commonui.viewholders.PersonViewHolder
import com.andradel.xous.core.util.extensions.inflate
import com.andradel.xous.search.R
import com.andradel.xous.search.ui.SearchItem

class SearchPersonAdapter(
    private val goToPerson: (Person) -> Unit
) : ListAdapter<SearchItem.PersonItem, PersonViewHolder>(ItemDiffUtils<SearchItem.PersonItem>()) {

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(getItem(position).item /*, goToPerson*/)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(parent.context.inflate(R.layout.person_viewholder, parent))
    }
}