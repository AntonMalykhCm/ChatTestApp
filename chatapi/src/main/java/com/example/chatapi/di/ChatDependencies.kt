package com.example.chatapi.di

import com.example.chatapi.ChatMiddleware
import com.example.chatapi.ChatReducer
import com.example.chatapi.ChatState

interface ChatDependencies<State: ChatState> {
    fun getChatMiddleware(): ChatMiddleware<State>
    fun getChatReducer(): ChatReducer<State>
}