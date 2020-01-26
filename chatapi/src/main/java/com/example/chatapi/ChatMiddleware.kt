package com.example.chatapi

interface ChatMiddleware<S : ChatState> {
    fun apply(state: S?, intent: ChatIntent, actionSupplier: ChatActionSupplier): ChatIntent
}