package com.example.chatapi.di.incoming

import com.example.chatapi.ChatAction
import com.example.chatapi.ChatState

interface ChatReducer<S : ChatState> {
    fun reduce(state: S?, action: ChatAction): S
}