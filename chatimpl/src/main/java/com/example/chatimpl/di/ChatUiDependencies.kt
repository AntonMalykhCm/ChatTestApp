package com.example.chatimpl.di

import com.example.chatapi.ChatActionSupplier
import com.example.chatapi.ChatStore
import com.example.chatimpl.data.ChatStateImpl

interface ChatUiDependencies {

    fun getChatActionSupplier(): ChatActionSupplier
    fun getChatStore(): ChatStore<ChatStateImpl>
}