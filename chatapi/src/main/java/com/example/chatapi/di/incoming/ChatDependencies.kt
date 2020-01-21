package com.example.chatapi.di.incoming

import com.example.chatapi.ChatState
import com.example.chatapi.di.incoming.ChatFragmentProvider
import com.example.chatapi.di.incoming.ChatMiddleware
import com.example.chatapi.di.incoming.ChatReducer

interface ChatDependencies<State: ChatState> {
    fun getChatFragmentProvider(): ChatFragmentProvider<State>
    fun getChatMiddleware(): ChatMiddleware<State>
    fun getChatReducer(): ChatReducer<State>
}