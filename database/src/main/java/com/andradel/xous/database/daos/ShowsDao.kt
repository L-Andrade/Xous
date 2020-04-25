package com.andradel.xous.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andradel.xous.database.models.ShowDomain
import com.andradel.xous.database.models.ShowType
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ShowsDao {
    @Query("SELECT * FROM shows WHERE type = :type ORDER BY createdAt DESC LIMIT 20")
    fun getShowsByType(type: ShowType): Flow<List<ShowDomain>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(show: ShowDomain)
}