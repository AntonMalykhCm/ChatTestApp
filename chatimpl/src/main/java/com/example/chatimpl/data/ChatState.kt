package com.example.chatimpl.data

import com.example.mvifeatureapi.api.State

private var ID_COUNTER = 0

/**
 * Provides id for the message.
 */
internal fun getNextMessageId() = ID_COUNTER++

/**
 * State of the chat feature.
 */
data class ChatState(
    val headerState: HeaderState,
    val feedState: FeedState,
    val newMessageState: NewMessageState
) : State {

    data class HeaderState(
        val chatName: String,
        val messageCount: Int
    )

    data class FeedState(
        val messages: List<Message>
    )

    data class NewMessageState(
        val message: String
    )

    data class Message(
        val isIncoming: Boolean,
        val text: String,
        val isLocal: Boolean,
        val id: Int
    )
}