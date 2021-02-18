package com.andradel.xous.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.andradel.xous.database.converters.Converters
import com.andradel.xous.database.daos.ShowsDao
import com.andradel.xous.database.models.ShowDomain

@TypeConverters(Converters::class)
@Database(entities = [ShowDomain::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun showsDao(): ShowsDao
}