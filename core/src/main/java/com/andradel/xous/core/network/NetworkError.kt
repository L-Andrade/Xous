package com.andradel.xous.core.network

import androidx.annotation.StringRes
import com.andradel.xous.core.models.BaseError

sealed class NetworkError : BaseError {
    data class NoNetwork(@StringRes override val message: Int) : NetworkError()
    data class Generic(@StringRes override val message: Int) : NetworkError()
}