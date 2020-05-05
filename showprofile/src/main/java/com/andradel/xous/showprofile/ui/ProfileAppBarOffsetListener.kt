package com.andradel.xous.showprofile.ui

import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.show_profile_fragment.view.*

object ProfileAppBarOffsetListener : AppBarLayout.OnOffsetChangedListener {

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val totalScroll = appBarLayout.totalScrollRange.toFloat()
        val alpha = (totalScroll + verticalOffset) / totalScroll
        appBarLayout.posterCard?.alpha = alpha
        appBarLayout.toolbar?.navigationIcon?.alpha = ((1 - alpha) * 255).toInt()
    }
}