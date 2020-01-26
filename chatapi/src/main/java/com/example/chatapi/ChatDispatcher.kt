package com.example.chatapi

import io.reactivex.Observable

internal interface ChatDispatcher : ChatActionSupplier {
    fun observeChatAction(): Observable<ChatIntent>
}