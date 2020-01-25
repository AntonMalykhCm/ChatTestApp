package com.example.chatimpl

import com.example.chatapi.ChatAction
import com.example.chatapi.ChatMiddleware
import com.example.chatapi.ChatActionSupplier
import com.example.chatimpl.data.ChatStateImpl

class ChatMiddlewareImpl: ChatMiddleware<ChatStateImpl> {

    override fun apply(
        state: ChatStateImpl?,
        action: ChatAction,
        actionSupplier: ChatActionSupplier
    ): ChatAction {
        return action
    }
}