package com.md.geminiaijetpackcompose.presentation.chat

import androidx.compose.runtime.toMutableStateList
import com.md.geminiaijetpackcompose.domain.model.ChatMessage

class ChatUiState(
    messages: List<ChatMessage> = emptyList()
) {
    private val _messages: MutableList<ChatMessage> = messages.toMutableStateList()
    val messages: List<ChatMessage> = _messages

    fun addMessage(msg: ChatMessage) {
        _messages.add(msg)
    }

    fun replaceLastPendingMessage() {
        val lastMessage = _messages.lastOrNull()
        lastMessage?.let {
            val newMessage = lastMessage.apply { isFetching = false }
            _messages.removeLast()
            _messages.add(newMessage)
        }
    }
}