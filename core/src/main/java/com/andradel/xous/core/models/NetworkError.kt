package com.andradel.xous.core.models

import com.andradel.xous.core.R
import com.andradel.xous.core.stringresolver.StringResolver

sealed class NetworkError<out T> {
    data class NoNetwork<T>(val throwable: Throwable) : NetworkError<T>()
    data class Generic<T>(val throwable: Throwable) : NetworkError<T>()

    fun resolve(resolver: StringResolver): String = when(this) {
        is NoNetwork -> resolver[R.string.no_network_error]
        is Generic -> resolver[R.string.generic_network_error]
    }
}