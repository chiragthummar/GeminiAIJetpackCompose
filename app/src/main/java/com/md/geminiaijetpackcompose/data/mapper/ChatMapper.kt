package com.md.geminiaijetpackcompose.data.mapper

import com.md.geminiaijetpackcompose.data.local.ChatMessageEntity
import com.md.geminiaijetpackcompose.domain.model.ChatMessage
import java.util.Date


fun ChatMessageEntity.toChatMessage(): ChatMessage {
    return ChatMessage(
        isFetching = isFetching,
        text = text,
        role = role,
        id = id
    )
}

fun ChatMessage.toChatMessageEntity(): ChatMessageEntity {
    return ChatMessageEntity(
        id = id,
        text = text,
        role = role,
        msgTime = Date(),
        isMine = false,
        isFetching = isFetching,
        isFavorite = false
    )
}
