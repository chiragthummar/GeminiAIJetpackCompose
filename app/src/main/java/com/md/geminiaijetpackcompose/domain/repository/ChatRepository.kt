package com.md.geminiaijetpackcompose.domain.repository

import com.md.geminiaijetpackcompose.data.local.ChatMessageEntity
import com.md.geminiaijetpackcompose.domain.model.ChatMessage
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    fun getAllChatMessages() : Flow<List<ChatMessage>>

    suspend fun insertSingleMessage(
        chatMessageEntity: ChatMessageEntity
    )

    suspend fun insertChatMessages(
        chatMessages: List<ChatMessageEntity>
    )

    suspend fun clearChat()

}