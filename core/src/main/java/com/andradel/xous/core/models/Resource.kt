package com.andradel.xous.core.models

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error<out T>(val error: AbstractError) : Resource<T>()

    companion object {
        fun <T> success(data: T) = Success(data)
        fun <T> failed(error: AbstractError) = Error<T>(error)
    }
}