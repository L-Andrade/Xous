package com.andradel.xous.database.converters

import androidx.room.TypeConverter
import com.andradel.xous.database.models.ShowType

internal class Converters {
    @TypeConverter
    fun toShowType(value: String) = ShowType.valueOf(value)

    @TypeConverter
    fun fromShowType(value: ShowType) = value.toString()
}