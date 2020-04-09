package com.andradel.xous.home.ui.model

import com.andradel.xous.common_models.internal.Show

sealed class ShowItem {
    abstract val id: Long

    data class Header(val title: String) : ShowItem() {
        override val id: Long = Long.MIN_VALUE
    }

    data class Item(val show: Show) : ShowItem() {
        override val id: Long = show.id
    }
}