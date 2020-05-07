package com.andradel.xous.core.util.extensions

import android.text.Spanned
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

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