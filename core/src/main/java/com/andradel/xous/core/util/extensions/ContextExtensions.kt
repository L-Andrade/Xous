package com.andradel.xous.core.util.extensions

import android.content.Context
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes

fun Context.inflate(
    @LayoutRes layoutId: Int,
    viewGroup: ViewGroup,
    attachToRoot: Boolean = false
): View = LayoutInflater.from(this).inflate(layoutId, viewGroup, attachToRoot)

@Suppress("SpreadOperator")
fun Context.getHtmlSpannedString(@StringRes id: Int, vararg args: Any): Spanned =
    getString(id, *args).toHtmlSpan()