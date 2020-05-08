package com.andradel.xous.commonui.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andradel.xous.commonmodels.internal.person.CastMember
import com.andradel.xous.commonmodels.internal.person.CrewMember
import com.andradel.xous.commonmodels.internal.person.Person
import com.andradel.xous.commonmodels.internal.person.RegularPerson
import com.andradel.xous.commonui.R
import com.andradel.xous.commonui.extensions.getString
import com.andradel.xous.commonui.extensions.loadCircleWithFade

class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.title)
    private val name: TextView = itemView.findViewById(R.id.name)
    private val image: ImageView = itemView.findViewById(R.id.image)

    fun bind(person: Person) {
        title.text = when {
            person is CrewMember && person.isCreator -> itemView.getString(R.string.creator)
            person is CrewMember -> person.job ?: person.department
            person is CastMember -> person.character
            person is RegularPerson -> person.department
            else -> ""
        }
        name.text = person.name
        image.loadCircleWithFade(person.profileUrl, R.drawable.ic_account_circle_24dp)
    }
}