package com.example.chatapi.di.incoming

import com.example.chatapi.ChatState

interface ChatDependencies<State: ChatState> {
    fun getChatMiddleware(): ChatMiddleware<State>
    fun getChatReducer(): ChatReducer<State>
}