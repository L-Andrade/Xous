package com.andradel.xous.core.models

sealed class NetworkError(val message: String) {
    class NoNetwork(message: String) : NetworkError(message)
    class Generic(message: String) : NetworkError(message)
}