package com.example.chatimpl.data

import com.example.chatapi.ChatState

internal data class ChatStateImpl(
    val headerState: HeaderState,
    val feedState: FeedState,
    val messageState: MessageState
) : ChatState {

    data class HeaderState(
        val chatName: String,
        val messageCount: Int
    )

    data class FeedState(
        val messages: List<Message>
    )

    data class MessageState(
        val message: String
    )

    data class Message(
        val isIncoming: Boolean,
        val text: String
    )
}