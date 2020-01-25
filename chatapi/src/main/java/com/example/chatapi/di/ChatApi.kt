package com.example.chatapi.di

import com.example.chatapi.ChatStarter
import com.example.chatapi.ChatState

interface ChatApi {
    companion object {
        fun <State : ChatState> get(dependencies: ChatDependencies<State>): ChatApi {
            return DaggerChatApiComponent
                .factory()
                .create(dependencies as ChatDependencies<ChatState>)
        }
    }

    fun getChatStarter() : ChatStarter
}