package com.andradel.xous.core.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.andradel.xous.core.models.Schedulers
import com.andradel.xous.core.stringresolver.StringResolver
import com.andradel.xous.core.stringresolver.StringResolverImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import javax.inject.Singleton

@Module
class CoreModule {
    @Provides
    fun provideSchedulers(): Schedulers =
        Schedulers(Dispatchers.IO, Dispatchers.Default, Dispatchers.Main)

    @Provides
    @Singleton
    fun provideKotlinSerializer(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideKotlinSerializerAdapter(json: Json): Converter.Factory {
        val mediaType = MEDIA_TYPE.toMediaType()
        return json.asConverterFactory(mediaType)
    }

    @Provides
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    fun provideContext(application: Application): Context = application

    @Provides
    fun provideStringResolver(impl: StringResolverImpl): StringResolver = impl

    companion object {
        private const val MEDIA_TYPE = "application/json"
    }
}