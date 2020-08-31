package com.andradel.xous.core.network

import com.andradel.xous.core.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

object ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val url: HttpUrl = original.url.newBuilder()
            .addQueryParameter(API_KEY, BuildConfig.API_KEY)
            .build()
        val requestBuilder: Request.Builder = original.newBuilder().url(url)
        return chain.proceed(requestBuilder.build())
    }

    private const val API_KEY = "api_key"
}