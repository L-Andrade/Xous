package com.andradel.xous.showprofile.ui.adapter

import com.andradel.xous.common_models.internal.BaseShow
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.showprofile.model.Creator
import com.andradel.xous.showprofile.model.Season
import com.andradel.xous.showprofile.ui.adapter.subadapters.CreatorAdapter
import com.andradel.xous.showprofile.ui.adapter.subadapters.SeasonAdapter
import com.andradel.xous.showprofile.ui.adapter.subadapters.SimilarShowAdapter

sealed class ProfileItem {
    data class Header(val title: String) : ProfileItem()
    data class Overview(val show: BaseShow) : ProfileItem()

    class SimilarShowList(shows: List<Show>, goToShow: (Show) -> Unit) : ProfileItem() {
        val adapter = SimilarShowAdapter(goToShow).also { it.submitList(shows) }
    }

    class CreatorList(creators: List<Creator>) : ProfileItem() {
        val adapter = CreatorAdapter().also { it.submitList(creators) }
    }

    class SeasonList(seasons: List<Season>) : ProfileItem() {
        val adapter = SeasonAdapter().also { it.submitList(seasons) }
    }
}