package com.andradel.xous.commonui.extensions

import android.view.View
import androidx.core.view.isVisible

fun View.animateOut() {
    this.animate().alpha(0f)
    isVisible = false
}

fun View.animateIn() {
    isVisible = true
    this.animate().alpha(1f)
}