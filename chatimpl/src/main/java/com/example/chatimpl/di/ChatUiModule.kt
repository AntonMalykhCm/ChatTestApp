package com.example.chatimpl.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ChatUiModule {

    @Singleton
    @Provides
    fun provideChatActionSupplier(dependencies: ChatUiDependencies) = dependencies.getChatActionSupplier()

    @Singleton
    @Provides
    fun provideChatStore(dependencies: ChatUiDependencies) = dependencies.getChatStore()
}
