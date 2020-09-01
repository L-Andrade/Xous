package com.andradel.xous.core.models

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error<out T>(val error: BaseError) : Resource<T>()

    companion object {
        fun <T> success(data: T) = Success(data)
        fun <T> failed(error: BaseError) = Error<T>(error)
    }
}