package com.andradel.xous.core.util.extensions

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

fun View.animateOut() {
    this.animate().alpha(0f)
    isVisible = false
}

fun View.animateIn() {
    isVisible = true
    this.animate().alpha(1f)
}

fun ImageView.loadWithFade(url: String?) {
    if (url != null && url.isNotBlank()) {
        Glide.with(this).load(url)
            .transition(withCrossFade())
            .into(this)
    } else {
        Glide.with(this).clear(this)
    }
}