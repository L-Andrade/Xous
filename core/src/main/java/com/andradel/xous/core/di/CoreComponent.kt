package com.andradel.xous.core.di

import android.app.Application
import com.andradel.xous.core.models.Schedulers
import com.andradel.xous.core.stringresolver.StringResolver
import com.andradel.xous.database.datasources.RecentlyViewedDataSource
import com.andradel.xous.database.di.DatabaseModule
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = [CoreModule::class, NetworkModule::class, DatabaseModule::class])
@Singleton
interface CoreComponent {

    @Component.Builder
    interface Builder {
        fun build(): CoreComponent

        @BindsInstance
        fun application(application: Application): Builder
    }

    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelFactory
    fun provideTMDBRetrofit(): Retrofit
    fun provideSchedulers(): Schedulers
    fun provideStringResolver(): StringResolver
    fun provideRecentlyViewedDataSource(): RecentlyViewedDataSource
}