package com.andradel.xous.core.util

import com.andradel.xous.core.R
import com.andradel.xous.core.models.NetworkError
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.models.Resource.Companion.failed
import com.andradel.xous.core.models.Resource.Companion.success
import com.andradel.xous.core.stringresolver.StringResolver
import retrofit2.HttpException
import java.io.IOException

inline fun <T> safeApiCall(resolver: StringResolver, call: () -> T): Resource<T> = try {
    success(call())
} catch (e: IOException) {
    failed(NetworkError.NoNetwork(resolver[R.string.no_network_error]))
} catch (e: HttpException) {
    // This will probably get more detailed as needed using e.code()
    failed(NetworkError.Generic(resolver[R.string.generic_network_error]))
}