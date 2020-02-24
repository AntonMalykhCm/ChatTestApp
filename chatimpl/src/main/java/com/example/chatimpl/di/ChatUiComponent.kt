package com.example.chatimpl.di

import com.example.chatimpl.di.modules.ChatUiModule
import com.example.chatimpl.ui.ChatActivity
import com.example.chatimpl.ui.ChatFragmentBase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ChatUiModule::class
    ]
)
internal interface ChatUiComponent {

    fun inject(fragment: ChatFragmentBase)
    fun inject(activity: ChatActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance chatUiDependencies: ChatUiDependencies): ChatUiComponent
    }
}