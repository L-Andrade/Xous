package com.andradel.xous.core.di

import android.app.Application
import com.andradel.xous.core.models.Schedulers
import com.andradel.xous.database.datasources.RecentlyViewedDataSource
import com.andradel.xous.scopes.AppScope
import com.andradel.xous.scopes.SingleIn
import com.squareup.anvil.annotations.MergeComponent
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@SingleIn(AppScope::class)
@MergeComponent(AppScope::class)
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): CoreComponent
    }

    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelFactory
    fun provideTMDBRetrofit(): Retrofit
    fun provideSchedulers(): Schedulers
    fun provideRecentlyViewedDataSource(): RecentlyViewedDataSource
}