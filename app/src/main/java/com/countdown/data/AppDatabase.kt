package com.countdown.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.countdown.data.dao.CountdownDao
import com.countdown.data.entity.CountdownEntity
import com.countdown.data.mappers.Converters


@Database(
    entities = [CountdownEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val countdownDao: CountdownDao
}