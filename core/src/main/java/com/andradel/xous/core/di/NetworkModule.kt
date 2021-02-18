package com.andradel.xous.core.di

import com.andradel.xous.core.BuildConfig
import com.andradel.xous.core.network.ApiKeyInterceptor
import com.andradel.xous.scopes.AppScope
import com.andradel.xous.scopes.SingleIn
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Converter
import retrofit2.Retrofit

@Module
@ContributesTo(AppScope::class)
class NetworkModule {

    @Provides
    @SingleIn(AppScope::class)
    @IntoSet
    fun provideOkHttpInterceptor(): Interceptor {
        val level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
        return HttpLoggingInterceptor().setLevel(level)
    }

    @Provides
    @IntoSet
    fun provideOkHttpAPIKeyInterceptor(): Interceptor = ApiKeyInterceptor

    @Provides
    @SingleIn(AppScope::class)
    fun provideOkHttpClient(interceptors: @JvmSuppressWildcards Set<Interceptor>): OkHttpClient {
        val builder = OkHttpClient.Builder()
        interceptors.forEach { builder.addInterceptor(it) }
        return builder.build()
    }

    @Provides
    @SingleIn(AppScope::class)
    fun provideTMDBRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(TMDB_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(converterFactory).build()

    companion object {
        private const val TMDB_BASE_URL = "https://api.themoviedb.org/"
    }
}