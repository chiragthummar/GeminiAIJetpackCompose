package com.md.geminiaijetpackcompose.domain.model

import java.util.UUID


enum class Role {
    USER, AI, ERROR
}

data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    var text: String = "",
    val role: Role ,
    var isFetching: Boolean = false
)
