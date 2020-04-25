package com.andradel.xous.core.util.extensions

import android.text.Spanned
import androidx.annotation.StringRes
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

inline fun <T> Fragment.observe(liveData: LiveData<T>, crossinline onChanged: (T) -> Unit) =
    liveData.observe(viewLifecycleOwner, Observer { onChanged(it) })

fun Fragment.showSnackbar(message: String, duration: Int = BaseTransientBottomBar.LENGTH_SHORT) {
    Snackbar.make(requireView(), message, duration).show()
}

fun Fragment.getHtmlSpannedString(@StringRes id: Int, vararg args: Any): Spanned =
    getString(id, *args).toHtmlSpan()

fun String.toHtmlSpan(): Spanned = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)