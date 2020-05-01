package com.andradel.xous.core.util.extensions

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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

fun View.getString(@StringRes id: Int) = this.resources.getString(id)

// Image loading could be better
fun ImageView.loadWithFade(url: String?, @DrawableRes placeholder: Int? = null) {
    if (url != null && url.isNotBlank()) {
        Glide.with(this).load(url)
            .placeholder(placeholder ?: R.color.colorAccent)
            .transition(withCrossFade())
            .into(this)
    } else {
        Glide.with(this).clear(this)
    }
}

fun ImageView.loadCircleWithFade(url: String?, @DrawableRes placeholder: Int? = null) {
    if (url != null && url.isNotBlank()) {
        Glide.with(this).load(url)
            .placeholder(placeholder ?: R.color.colorAccent)
            .transition(withCrossFade())
            .apply(circleCropTransform())
            .into(this)
    } else {
        Glide.with(this).clear(this)
    }
}