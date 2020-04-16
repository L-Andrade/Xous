package com.andradel.xous.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andradel.xous.common_models.internal.Show
import java.util.*

@Entity(tableName = "shows")
data class ShowDomain(
    @PrimaryKey
    val id: Int,
    val name: String,
    val posterUrl: String?,
    val rating: Float,
    val backdropUrl: String?,
    val firstAired: String,
    val overview: String,
    val type: ShowType,
    val createdAt: Long = Date().time
) {
    fun toInternal(): Show =
        Show(
            id = id,
            name = name,
            posterUrl = posterUrl,
            rating = rating,
            backdropUrl = backdropUrl,
            firstAired = firstAired,
            overview = overview
        )
}

fun Show.toDomain(type: ShowType) =
    ShowDomain(
        id = id,
        name = name,
        posterUrl = posterUrl,
        rating = rating,
        backdropUrl = backdropUrl,
        firstAired = firstAired,
        overview = overview,
        type = type
    )

enum class ShowType {
    RECENTLY_VIEWED
}