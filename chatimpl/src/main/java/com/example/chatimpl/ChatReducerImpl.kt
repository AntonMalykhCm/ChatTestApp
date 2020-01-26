package com.example.chatimpl

import com.example.chatapi.ChatIntent
import com.example.chatapi.ChatStartIntent
import com.example.chatapi.ChatReducer
import com.example.chatimpl.data.ChatStateImpl
import com.example.chatimpl.data.intents.NewMessageIntent

class ChatReducerImpl : ChatReducer<ChatStateImpl> {

    override fun reduce(state: ChatStateImpl?, intent: ChatIntent): ChatStateImpl {
        return when (intent) {
            is ChatStartIntent -> getDefaultChatState()
            is NewMessageIntent -> getStateWithNewMessage(state, intent)
            else -> state ?: getDefaultChatState()
        }
    }

    private fun getStateWithNewMessage(
        state: ChatStateImpl?,
        action: NewMessageIntent
    ): ChatStateImpl {
        val state = state ?: getDefaultChatState()
        val headerState =
            ChatStateImpl.HeaderState(
                state.headerState.chatName,
                state.headerState.messageCount + 1
            )
        val feedState =
            ChatStateImpl.FeedState(
                state.feedState.messages.toMutableList()
                    .also {
                        it.add(ChatStateImpl.Message(false, action.message))
                    }
            )
        val messageState = ChatStateImpl.MessageState("")
        return ChatStateImpl(headerState, feedState, messageState)
    }

    private fun getDefaultChatState(): ChatStateImpl {
        return ChatStateImpl(
            ChatStateImpl.HeaderState(
                "Чад кутежа",
                3
            ),
            ChatStateImpl.FeedState(
                listOf(
                    ChatStateImpl.Message(
                        true,
                        "Привет!"
                    ),
                    ChatStateImpl.Message(
                        false,
                        "Привет!"
                    ),
                    ChatStateImpl.Message(
                        true,
                        "Как дела?"
                    )
                )
            ),
            ChatStateImpl.MessageState(
                "Отлич"
            )
        )
    }
}