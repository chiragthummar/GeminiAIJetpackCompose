package com.md.geminiaijetpackcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ChatMessageEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    Converters::class
)
abstract class ChatDatabase : RoomDatabase() {
    abstract val chatDao : ChatDao
}