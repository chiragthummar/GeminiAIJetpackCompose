package com.md.geminiaijetpackcompose.data.repository

import com.md.geminiaijetpackcompose.data.local.ChatDatabase
import com.md.geminiaijetpackcompose.data.local.ChatMessageEntity
import com.md.geminiaijetpackcompose.data.mapper.toChatMessage
import com.md.geminiaijetpackcompose.domain.model.ChatMessage
import com.md.geminiaijetpackcompose.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepositoryImpl @Inject constructor(
    db: ChatDatabase
) : ChatRepository {

    val dao = db.chatDao

    override fun getAllChatMessages(): Flow<List<ChatMessage>> {
        return dao.getAllChatMessage().map { it.map { msg -> msg.toChatMessage() } }
    }

    override suspend fun insertSingleMessage(chatMessageEntity: ChatMessageEntity) {
        dao.insertSingleMessage(chatMessageEntity)
    }

    override suspend fun insertChatMessages(chatMessages: List<ChatMessageEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun clearChat() {
        TODO("Not yet implemented")
    }
}