package com.andradel.xous.commonui.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.andradel.xous.commonui.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

// Not the best, but suffices for now
fun ImageView.load(
    url: String?,
    @DrawableRes placeholder: Int = R.color.colorAccent,
    @DrawableRes error: Int = R.color.colorAccent,
    fade: Boolean = true
) {
    val request = Glide.with(this).load(url)
        .placeholder(placeholder)
        .error(error)

    if (fade) {
        request.transition(withCrossFade()).into(this)
    } else {
        request.into(this)
    }
}

suspend fun Context.downloadImage(image: String): Bitmap = suspendCancellableCoroutine { cont ->
    val target = object : CustomTarget<Bitmap>() {
        override fun onLoadCleared(placeholder: Drawable?) {
            cont.cancel()
        }

        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            cont.resume(resource)
        }

        override fun onLoadFailed(errorDrawable: Drawable?) {
            cont.cancel()
        }
    }

    val manager = Glide.with(this)
    manager.asBitmap().load(image).into(target)

    cont.invokeOnCancellation {
        manager.clear(target)
    }
}