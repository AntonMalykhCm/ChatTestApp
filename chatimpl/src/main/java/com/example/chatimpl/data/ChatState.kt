package com.example.chatimpl.data

import com.example.mvifeatureapi.api.State

const val ID_NOT_LOCAL = -1
private var ID_COUNTER = 0

fun getNextMessageId() = ID_COUNTER++

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