package com.andradel.xous.core.di

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    @IntoSet
    fun provideOkHttpInterceptor(): Interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    @IntoSet
    fun provideOkHttpAPIKeyInterceptor(): Interceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original: Request = chain.request()
            // TODO: USE THE PROPER ONE, JUST SEEING IF IT WORKS
            val url: HttpUrl = original.url.newBuilder()
                .addQueryParameter(API_KEY, "1362fcf614bff73571d6952adbb37701")
                .build()
            val requestBuilder: Request.Builder = original.newBuilder().url(url)
            return chain.proceed(requestBuilder.build())
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptors: MutableSet<Interceptor>): OkHttpClient {
        val builder = OkHttpClient.Builder()
        interceptors.forEach { builder.addInterceptor(it) }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideTMDBRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(TMDB_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(converterFactory).build()

    companion object {
        private const val API_KEY = "api_key"
        private const val TMDB_BASE_URL = "https://api.themoviedb.org/"
    }
}