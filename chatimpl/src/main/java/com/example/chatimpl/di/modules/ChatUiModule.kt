package com.example.chatimpl.di.modules

import com.example.chatimpl.di.ChatUiDependencies
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class ChatUiModule {

    @Singleton
    @Provides
    fun provideChatIntentDispatcher(dependencies: ChatUiDependencies) =
        dependencies.getMviFeatureApi().getIntentDispatcher()

    @Singleton
    @Provides
    fun provideChatStore(dependencies: ChatUiDependencies) =
        dependencies.getMviFeatureApi().getStore()
}
