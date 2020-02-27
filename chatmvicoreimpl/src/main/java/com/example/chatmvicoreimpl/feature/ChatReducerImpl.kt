package com.example.chatmvicoreimpl.feature

import android.content.Intent
import android.util.Log
import com.badoo.mvicore.element.Reducer
import com.example.chatmvicoreimpl.data.ChatState
import com.example.chatmvicoreimpl.data.ChatState.Companion.getDefaultChatState
import com.example.chatmvicoreimpl.data.Effect
import com.example.chatmvicoreimpl.data.RenderMessageEffect
import com.example.chatmvicoreimpl.data.Start
import javax.inject.Inject

/**
 * [Reducer] implementation to provide new [ChatState] from incoming intents [Intent].
 */
class ChatReducerImpl @Inject constructor() : Reducer<ChatState, Effect> {

    override fun invoke(state: ChatState, effect: Effect): ChatState {
        val outState = when (effect) {
            is Start -> getDefaultChatState()
            is RenderMessageEffect -> getStateWithNewMessage(state, effect)
        }
        Log.d("ChatReducerImpl", "State:\n$outState")
        return outState
    }


    private fun getStateWithNewMessage(
        state: ChatState?,
        intent: RenderMessageEffect
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
}