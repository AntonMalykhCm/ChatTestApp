package com.example.chatimpl

import com.example.chatimpl.data.ChatState
import com.example.chatimpl.data.getNextMessageId
import com.example.chatimpl.data.intents.RenderMessageIntent
import com.example.mvifeatureapi.api.Intent
import com.example.mvifeatureapi.api.Reducer
import com.example.mvifeatureapi.api.StartIntent

class ChatReducerImpl : Reducer<ChatState> {

    override fun reduce(state: ChatState?, intent: Intent): ChatState {
        return when (intent) {
            is StartIntent -> getDefaultChatState()
            is RenderMessageIntent -> getStateWithNewMessage(state, intent)
            else -> state ?: getDefaultChatState()
        }
    }

    private fun getStateWithNewMessage(
        state: ChatState?,
        intent: RenderMessageIntent
    ): ChatState {
        val state = state ?: getDefaultChatState()
        var isNewMessage = true
        val newMessageList = mutableListOf<ChatState.Message>()
        val feedState =
            ChatState.FeedState(
                state.feedState.messages.fold(
                    newMessageList,
                    { acc, message ->
                        val toAdd =
                            if (message.id == intent.id) {
                                isNewMessage = false
                                ChatState.Message(
                                    intent.isIncoming,
                                    intent.message,
                                    intent.isLocal,
                                    message.id
                                )
                            } else message
                        acc.add(toAdd)
                        acc
                    }
                )
            )
        if (isNewMessage) {
            newMessageList.add(
                ChatState.Message(
                    intent.isIncoming,
                    intent.message,
                    intent.isLocal,
                    intent.id
                )
            )
        }
        val headerState =
            ChatState.HeaderState(
                state.headerState.chatName,
                newMessageList.size
            )
        val messageState =
            if (intent.isLocal) ChatState.NewMessageState("")
            else state.newMessageState
        return ChatState(headerState, feedState, messageState)
    }

    private fun getDefaultChatState(): ChatState {
        return ChatState(
            ChatState.HeaderState(
                "Чад кутежа",
                3
            ),
            ChatState.FeedState(
                listOf(
                    ChatState.Message(
                        true,
                        "Привет!",
                        false,
                        getNextMessageId()
                    ),
                    ChatState.Message(
                        false,
                        "Привет!",
                        false,
                        getNextMessageId()
                    ),
                    ChatState.Message(
                        true,
                        "Как дела?",
                        false,
                        getNextMessageId()
                    )
                )
            ),
            ChatState.NewMessageState(
                "Отлич"
            )
        )
    }
}