package com.andradel.xous.core.network

import com.andradel.xous.core.models.BaseError

sealed class NetworkError : BaseError {
    data class NoNetwork(override val message: String) : NetworkError()
    data class Generic(override val message: String) : NetworkError()
}