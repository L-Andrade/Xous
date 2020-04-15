package com.andradel.xous.showprofile.ui.adapter

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.andradel.xous.showprofile.R

object BackdropParallax : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val parallaxView = page.findViewById<View>(R.id.backdrop)
        when {
            position < -1 -> // This page is way off-screen to the left.
                page.alpha = 1f
            position <= 1 -> { // [-1,1]
                parallaxView.translationX = -position * (page.width)
            }
            else -> // This page is way off-screen to the right.
                page.alpha = 1f
        }
    }
}