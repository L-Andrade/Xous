package com.andradel.xous.core.network

import com.andradel.xous.core.models.AbstractError

sealed class NetworkError : AbstractError() {
    data class NoNetwork(override val message: String) : NetworkError()
    data class Generic(override val message: String) : NetworkError()
}