package com.example.chatapi.di.outcoming

import com.example.chatapi.ChatState
import com.example.chatapi.di.DaggerChatApiComponent
import com.example.chatapi.di.incoming.ChatDependencies

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