package com.andradel.xous.core.models

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()

    companion object {
        fun <T> success(data: T) = Success(data)
        fun failed(message: String) = Error(message)
    }
}