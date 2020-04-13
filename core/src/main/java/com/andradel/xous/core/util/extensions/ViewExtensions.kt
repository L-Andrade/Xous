package com.andradel.xous.core.util.extensions

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import com.andradel.xous.core.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions.circleCropTransform

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
            .placeholder(R.color.colorAccent)
            .transition(withCrossFade())
            .into(this)
    } else {
        Glide.with(this).clear(this)
    }
}

fun ImageView.loadCircleWithFade(url: String?, @DrawableRes fallback: Int? = null) {
    if (url != null && url.isNotBlank()) {
        val base = Glide.with(this).load(url)
            .transition(withCrossFade())
            .apply(circleCropTransform())

        if (fallback == null) base.into(this) else base.fallback(fallback).into(this)
    } else {
        Glide.with(this).clear(this)
    }
}