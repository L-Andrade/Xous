package com.andradel.xous.commonui

import android.view.View
import androidx.annotation.IdRes
import androidx.viewpager2.widget.ViewPager2

class ViewPager2ParallaxPage(@IdRes private val viewId: Int) : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val parallaxView: View? = page.findViewById(viewId)
        when {
            position < -1 -> // This page is way off-screen to the left.
                page.alpha = 1f
            position <= 1 -> { // [-1,1]
                parallaxView?.translationX = -position * (page.width)
            }
            else -> // This page is way off-screen to the right.
                page.alpha = 1f
        }
    }
}