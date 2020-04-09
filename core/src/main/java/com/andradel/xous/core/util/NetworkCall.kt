package com.andradel.xous.core.util

import com.andradel.xous.core.models.Resource

// TODO: Improve
inline fun <T> safeApiCall(
    success: () -> Resource<T>, error: (message: String) -> Resource<T>
) = try {
    success()
} catch (e: Exception) {
    error("$e")
}