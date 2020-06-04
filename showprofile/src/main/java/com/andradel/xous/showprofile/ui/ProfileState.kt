package com.andradel.xous.showprofile.ui

import com.andradel.xous.commonmodels.internal.show.BaseShow
import com.andradel.xous.showprofile.ui.adapter.ProfileItem

data class ProfileState(
    val items: List<ProfileItem>,
    val show: BaseShow
)