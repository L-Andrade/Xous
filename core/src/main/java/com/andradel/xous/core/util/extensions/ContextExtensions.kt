package com.andradel.xous.core.util.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun Context.inflate(
    @LayoutRes layoutId: Int,
    viewGroup: ViewGroup,
    attachToRoot: Boolean = false
): View = LayoutInflater.from(this).inflate(layoutId, viewGroup, attachToRoot)