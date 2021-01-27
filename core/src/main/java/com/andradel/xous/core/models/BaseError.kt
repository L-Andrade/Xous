package com.andradel.xous.core.models

import androidx.annotation.StringRes

interface BaseError {
    @get:StringRes
    val message: Int
}