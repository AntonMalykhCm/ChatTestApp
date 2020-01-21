package com.example.chatapi

import io.reactivex.Observable

interface ChatStore<State: ChatState> {
    fun observeChatState(): Observable<State>
}