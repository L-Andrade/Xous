package com.andradel.xous.core.util.extensions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.text.Spanned
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.andradel.xous.core.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

inline fun <T> Fragment.observe(liveData: LiveData<T>, crossinline onChanged: (T) -> Unit) =
    liveData.observe(viewLifecycleOwner, Observer { onChanged(it) })

inline fun Fragment.showSnackbar(
    @StringRes message: Int,
    duration: Int = BaseTransientBottomBar.LENGTH_SHORT,
    @StringRes actionTitle: Int? = null,
    crossinline action: () -> Any = {}
) = showSnackbar(getString(message), duration, actionTitle, action)

inline fun Fragment.showSnackbar(
    message: String,
    duration: Int = BaseTransientBottomBar.LENGTH_SHORT,
    @StringRes actionText: Int? = null,
    crossinline action: () -> Any = {}
) {
    val snackbar = Snackbar.make(requireView(), message, duration)
    if (actionText != null) snackbar.setAction(actionText) { action() }
    snackbar.show()
}

fun Fragment.getHtmlSpannedString(@StringRes id: Int, vararg args: Any): Spanned =
    getString(id, *args).toHtmlSpan()

suspend fun Fragment.downloadImage(image: String): Bitmap = suspendCancellableCoroutine { cont ->
    Glide.with(this).asBitmap().load(image).into(object : CustomTarget<Bitmap>() {
        override fun onLoadCleared(placeholder: Drawable?) {
            cont.cancel(IllegalStateException(getString(R.string.failed_image_download)))
        }

        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            cont.resume(resource)
        }

        override fun onLoadFailed(errorDrawable: Drawable?) {
            cont.cancel(IllegalStateException(getString(R.string.failed_image_download)))
        }
    })
}