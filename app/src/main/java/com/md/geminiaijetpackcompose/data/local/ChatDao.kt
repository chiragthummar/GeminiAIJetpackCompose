package com.md.geminiaijetpackcompose.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {

    @Upsert
    suspend fun insertSingleMessage(chatMessage: ChatMessageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChatMessages(chatMessages: List<ChatMessageEntity>)

    @Query("DELETE FROM chat")
    suspend fun deleteAllChatMessages()

    @Query("SELECT * FROM chat")
    fun getAllChatMessage(): Flow<List<ChatMessageEntity>>

}