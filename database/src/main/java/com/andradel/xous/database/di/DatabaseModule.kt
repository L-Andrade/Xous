package com.andradel.xous.database.di

import android.content.Context
import androidx.room.Room
import com.andradel.xous.database.AppDatabase
import com.andradel.xous.database.daos.ShowsDao
import com.andradel.xous.database.datasources.RecentlyViewedDataSource
import com.andradel.xous.database.datasources.RecentlyViewedDataSourceImpl
import com.andradel.xous.scopes.AppScope
import com.andradel.xous.scopes.SingleIn
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides

@Module
@ContributesTo(AppScope::class)
class DatabaseModule {

    @Provides
    @SingleIn(AppScope::class)
    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()

    @Provides
    fun provideShowDao(appDatabase: AppDatabase): ShowsDao = appDatabase.showsDao()

    @Provides
    fun provideRecentlyViewedDataSource(impl: RecentlyViewedDataSourceImpl): RecentlyViewedDataSource = impl

    companion object {
        const val DATABASE_NAME = "xous_database"
    }
}