package com.example.chatimpl

import com.example.chatapi.ChatAction
import com.example.chatapi.ChatStartAction
import com.example.chatapi.di.incoming.ChatReducer
import com.example.chatimpl.data.ChatStateImpl
import com.example.chatimpl.data.actions.NewMessageAction

class ChatReducerImpl : ChatReducer<ChatStateImpl> {

    override fun reduce(state: ChatStateImpl?, action: ChatAction): ChatStateImpl {
        return when (action) {
            is ChatStartAction -> getDefaultChatState()
            is NewMessageAction -> getStateWithNewMessage(state, action)
            else -> state ?: getDefaultChatState()
        }
    }

    private fun getStateWithNewMessage(
        state: ChatStateImpl?,
        action: NewMessageAction
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