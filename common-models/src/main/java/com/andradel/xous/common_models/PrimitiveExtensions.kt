package com.andradel.xous.common_models

fun Int?.orZero() = this ?: 0

fun Long?.orZero() = this ?: 0

fun Boolean?.orFalse() = this ?: false

fun Float?.orZero() = this ?: 0f