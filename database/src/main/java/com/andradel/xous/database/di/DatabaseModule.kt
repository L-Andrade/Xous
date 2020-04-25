package com.andradel.xous.database.di

import android.content.Context
import androidx.room.Room
import com.andradel.xous.database.AppDatabase
import com.andradel.xous.database.datasources.RecentlyViewedDataSource
import com.andradel.xous.database.datasources.RecentlyViewedDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()

    @Provides
    internal fun provideShowDao(appDatabase: AppDatabase) = appDatabase.showsDao()

    @Provides
    internal fun provideRecentlyViewedDataSource(impl: RecentlyViewedDataSourceImpl):
            RecentlyViewedDataSource = impl

    companion object {
        const val DATABASE_NAME = "xous_database"
    }
}