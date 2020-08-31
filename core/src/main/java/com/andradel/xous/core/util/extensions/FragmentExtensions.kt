package com.andradel.xous.core.util.extensions

import android.content.Context
import android.text.Spanned
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

inline fun <T> Fragment.observe(liveData: LiveData<T>, crossinline onChanged: (T) -> Unit) =
    liveData.observe(viewLifecycleOwner) { onChanged(it) }

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

@Suppress("SpreadOperator")
fun Fragment.getHtmlSpannedString(@StringRes id: Int, vararg args: Any): Spanned =
    getString(id, *args).toHtmlSpan()

fun Fragment.hideKeyboard() {
    val activity = requireActivity()
    val inputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val focusView = activity.currentFocus ?: View(activity)
    inputMethodManager.hideSoftInputFromWindow(focusView.windowToken, 0)
}