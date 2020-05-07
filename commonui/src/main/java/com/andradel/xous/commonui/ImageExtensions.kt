package com.andradel.xous.commonui

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions.circleCropTransform
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

// Image loading could be better
fun ImageView.loadWithFade(
    url: String?,
    @DrawableRes placeholder: Int = 0,
    @DrawableRes error: Int = placeholder
) {
    Glide.with(this).load(url)
        .placeholder(placeholder)
        .error(error)
        .transition(withCrossFade())
        .into(this)
}

fun ImageView.loadCircleWithFade(
    url: String?,
    @DrawableRes placeholder: Int = 0,
    @DrawableRes error: Int = placeholder
) {
    Glide.with(this).load(url)
        .placeholder(placeholder)
        .error(error)
        .transition(withCrossFade())
        .apply(circleCropTransform())
        .into(this)
}

suspend fun Fragment.downloadImage(image: String): Bitmap = suspendCancellableCoroutine { cont ->
    Glide.with(this).asBitmap().load(image).into(object : CustomTarget<Bitmap>() {
        override fun onLoadCleared(placeholder: Drawable?) {
            cont.cancel(IllegalStateException())
        }

        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            cont.resume(resource)
        }

        override fun onLoadFailed(errorDrawable: Drawable?) {
            cont.cancel(IllegalStateException())
        }
    })
}