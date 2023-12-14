package com.md.geminiaijetpackcompose.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.md.geminiaijetpackcompose.domain.model.Role
import java.util.Date


@Entity(tableName = "chat")
data class ChatMessageEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val role: Role,
    val msgTime: Date?,
    val isMine: Boolean,
    val isFavorite: Boolean,
    val isFetching : Boolean,
    val text: String,
)