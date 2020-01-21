package com.example.chatapi.di.incoming

import com.example.chatapi.ChatAction
import com.example.chatapi.ChatState
import com.example.chatapi.ChatActionSupplier

interface ChatMiddleware<S : ChatState> {
    fun apply(state: S?, action: ChatAction, actionSupplier: ChatActionSupplier): ChatAction
}