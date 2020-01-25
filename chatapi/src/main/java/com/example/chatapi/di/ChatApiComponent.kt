package com.example.chatapi.di

import com.example.chatapi.ChatState
import com.example.chatapi.di.incoming.ChatDependencies
import com.example.chatapi.di.outcoming.ChatApi
import com.example.chatapi.ui.ChatActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ChatApiModule::class
    ]
)
internal interface ChatApiComponent : ChatApi {

    fun inject(chatActivity: ChatActivity<ChatState>)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance dependencies: ChatDependencies<ChatState>): ChatApiComponent
    }
}