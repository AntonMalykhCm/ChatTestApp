package com.example.chatapi.di

import com.example.chatapi.ChatActionSupplier
import com.example.chatapi.ChatState
import com.example.chatapi.ChatStore
import com.example.chatapi.ChatStarter
import com.example.chatapi.ChatDispatcher
import com.example.chatapi.impl.ChatDispatcherImpl
import com.example.chatapi.impl.ChatStarterImpl
import com.example.chatapi.impl.ChatStoreImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class ChatApiModule {

    @Provides
    @Singleton
    fun provideChatDispatcher(impl: ChatDispatcherImpl): ChatDispatcher = impl

    @Provides
    @Singleton
    fun provideChatActionSupplier(impl: ChatDispatcher): ChatActionSupplier = impl

    @Provides
    @Singleton
    fun provideChatStarter(impl: ChatStarterImpl): ChatStarter = impl

    @Provides
    @Singleton
    fun provideChatStore(impl: ChatStoreImpl<ChatState>): ChatStore<ChatState> = impl

    @Provides
    @Singleton
    fun provideChatMiddleware(dependencies: ChatDependencies<ChatState>) = dependencies.getChatMiddleware()

    @Provides
    @Singleton
    fun provideChatReducer(dependencies: ChatDependencies<ChatState>) = dependencies.getChatReducer()
}
