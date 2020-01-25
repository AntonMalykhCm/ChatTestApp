package com.example.chatapi

interface ChatMiddleware<S : ChatState> {
    fun apply(state: S?, action: ChatAction, actionSupplier: ChatActionSupplier): ChatAction
}