package com.andradel.xous.showprofile.ui

import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.show_profile_fragment.view.*

object ProfileAppBarOffsetListener : AppBarLayout.OnOffsetChangedListener {

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val totalScroll = appBarLayout.totalScrollRange.toFloat()

        val startHeight = totalScroll * VIEW_START_PERCENTAGE
        val appearingAlpha = calculateAlphaForStartHeight(verticalOffset, startHeight, totalScroll)

        val endHeightPoster = totalScroll * VIEW_END_PERCENTAGE_POSTER
        val disappearingAlpha = calculateAlphaForEndHeight(verticalOffset, endHeightPoster)

        appBarLayout.poster?.alpha = disappearingAlpha
        appBarLayout.toolbar?.alpha = appearingAlpha
    }

    private fun calculateAlphaForStartHeight(verticalOffset: Int, startHeight: Float, totalScroll: Float): Float {
        return if (-verticalOffset >= startHeight) {
            val toScroll = totalScroll - startHeight
            val scrolledFromStart = -verticalOffset - startHeight
            1 - (toScroll - scrolledFromStart) / toScroll
        } else 0f
    }

    private fun calculateAlphaForEndHeight(verticalOffset: Int, endHeight: Float): Float {
        return if (-verticalOffset <= endHeight) {
            (endHeight + verticalOffset) / endHeight
        } else 0f
    }

    private const val VIEW_START_PERCENTAGE = 0.9f
    private const val VIEW_END_PERCENTAGE_POSTER = 0.5f
}