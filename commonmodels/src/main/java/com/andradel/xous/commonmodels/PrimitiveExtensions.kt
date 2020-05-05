package com.andradel.xous.commonmodels

// This will all be moved to an extensions/common module
fun Int?.orZero() = this ?: 0

fun Long?.orZero() = this ?: 0

fun Boolean?.orFalse() = this ?: false

fun Float?.orZero() = this ?: 0f

fun Float.format(decimals: Int = 1): String = "%.${decimals}f".format(this)