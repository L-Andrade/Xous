package com.andradel.xous.core.models

sealed class NetworkError<out T>(val message: String) {
    class NoNetwork<T>(message: String) : NetworkError<T>(message)
    class Generic<T>(message: String) : NetworkError<T>(message)
}