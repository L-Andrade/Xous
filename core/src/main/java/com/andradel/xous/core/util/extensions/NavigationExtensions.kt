package com.andradel.xous.core.util.extensions

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

private inline fun safeNavigate(block: () -> Unit) {
    try {
        block()
    } catch (e: IllegalArgumentException) {
        // Ignored. This is a bug with the Navigation component on double clicks
    }
}

fun View.goTo(@IdRes action: Int) = safeNavigate {
    findNavController().navigate(action)
}

fun View.goTo(directions: NavDirections) = safeNavigate {
    findNavController().navigate(directions)
}

fun Fragment.goTo(@IdRes action: Int) = safeNavigate {
    findNavController().navigate(action)
}

fun Fragment.goTo(directions: NavDirections) = safeNavigate {
    findNavController().navigate(directions)
}

/*
fun View.toLoginOrElse() {
    // TODO: If not authed -> Go to login
    // TODO: Or else -> Do block
}
 */