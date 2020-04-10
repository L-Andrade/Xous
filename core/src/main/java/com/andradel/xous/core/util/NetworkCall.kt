package com.andradel.xous.core.util

import com.andradel.xous.core.models.NetworkError
import com.andradel.xous.core.models.Resource
import com.andradel.xous.core.models.Resource.Companion.failed
import retrofit2.HttpException
import java.io.IOException

inline fun <T> safeApiCall(success: () -> Resource<T>) = try {
    success()
} catch (e: IOException) {
    failed(NetworkError.NoNetwork<T>(e))
} catch (e: HttpException) {
    // This will probably get more detailed as needed using e.code()
    failed(NetworkError.Generic<T>(e))
}