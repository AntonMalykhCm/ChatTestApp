package com.example.chatimpl

import com.example.chatapi.ChatIntent
import com.example.chatapi.ChatMiddleware
import com.example.chatapi.ChatActionSupplier
import com.example.chatimpl.data.ChatStateImpl

class ChatMiddlewareImpl: ChatMiddleware<ChatStateImpl> {

    override fun apply(
        state: ChatStateImpl?,
        intent: ChatIntent,
        actionSupplier: ChatActionSupplier
    ): ChatIntent {
        return intent
    }
}