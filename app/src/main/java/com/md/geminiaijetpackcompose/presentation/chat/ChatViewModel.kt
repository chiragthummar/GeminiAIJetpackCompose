package com.md.geminiaijetpackcompose.presentation.chat

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.asTextOrNull
import com.google.ai.client.generativeai.type.content
import com.md.geminiaijetpackcompose.data.mapper.toChatMessageEntity
import com.md.geminiaijetpackcompose.domain.model.ChatMessage
import com.md.geminiaijetpackcompose.domain.model.Role
import com.md.geminiaijetpackcompose.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatModel: GenerativeModel,
    private val chatRepository: ChatRepository,
) : ViewModel() {

    private val chat = chatModel.startChat()

    private val _uiState: MutableStateFlow<ChatUiState> =
        MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> =
        _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            chatRepository.getAllChatMessages().collect {
                _uiState.value = ChatUiState(it)
            }
        }
    }

    fun sendMessage(userMessage: String) {
        // Add a pending message

            val record = ChatMessage(
                text = userMessage,
                role = Role.USER,
                isFetching = true
            )



        viewModelScope.launch {
            Log.e(TAG, "Old Message: $record")

            chatRepository.insertSingleMessage(
                record.toChatMessageEntity()
            )

            try {
                val response = chat.sendMessage(userMessage)

                _uiState.value.replaceLastPendingMessage()

                response.text?.let { modelResponse ->
                   /* _uiState.value.addMessage(
                        ChatMessage(
                            text = modelResponse,
                            role = Role.AI,
                            isFetching = false
                        )
                    )*/

                    val newMessage = record.apply { isFetching = false }
                    Log.e(TAG, "sendMessage: $newMessage")
                    chatRepository.insertSingleMessage(newMessage.toChatMessageEntity())

                    chatRepository.insertSingleMessage(
                        ChatMessage(
                            text = modelResponse,
                            role = Role.AI,
                            isFetching = false
                        ).toChatMessageEntity()
                    )
                }
            } catch (e: Exception) {
                _uiState.value.replaceLastPendingMessage()
                _uiState.value.addMessage(
                    ChatMessage(
                        text = e.localizedMessage,
                        role = Role.ERROR
                    )
                )
            }
        }
    }


}