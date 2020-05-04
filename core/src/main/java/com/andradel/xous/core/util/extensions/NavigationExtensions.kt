package com.andradel.xous.core.util.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

private fun Fragment.safeNavigate(directions: NavDirections) {
    if (mayNavigate) findNavController().navigate(directions)
}

private const val NAV_DESTINATION_ID = 123987465

private val Fragment.mayNavigate: Boolean
    get() {
        val navControllerDestination = findNavController().currentDestination?.id
        val fragmentDestination = view?.getTag(NAV_DESTINATION_ID) ?: navControllerDestination

        return if (navControllerDestination == fragmentDestination) {
            view?.setTag(NAV_DESTINATION_ID, fragmentDestination)
            true
        } else {
            false
        }
    }

fun Fragment.goTo(directions: NavDirections) = safeNavigate(directions)

// fun Fragment.goTo(@IdRes action: Int) = safeNavigate(action)

// fun View.goTo(@IdRes action: Int) = safeNavigate(action)

// fun View.goTo(directions: NavDirections) = safeNavigate(directions)

/*
fun View.toLoginOrElse() {
    // TODO: If not authed -> Go to login
    // TODO: Or else -> Do block
}
 */