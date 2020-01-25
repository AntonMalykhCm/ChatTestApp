package com.example.chatapi

interface ChatReducer<S : ChatState> {
    fun reduce(state: S?, action: ChatAction): S
}