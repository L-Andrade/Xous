package com.andradel.xous.core.stringresolver

import androidx.annotation.StringRes

interface StringResolver {
    operator fun get(@StringRes id: Int): String
}