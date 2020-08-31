package com.andradel.xous.core.di

import com.andradel.xous.core.BuildConfig
import com.andradel.xous.core.network.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    @IntoSet
    fun provideOkHttpInterceptor(): Interceptor {
        val level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
        return HttpLoggingInterceptor().setLevel(level)
    }

    @Provides
    @IntoSet
    fun provideOkHttpAPIKeyInterceptor(): Interceptor = ApiKeyInterceptor

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptors: @JvmSuppressWildcards Set<Interceptor>): OkHttpClient {
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
        private const val TMDB_BASE_URL = "https://api.themoviedb.org/"
    }
}