package com.example.chatmvicoreimpl.di

import android.content.Context
import com.example.chatmvicoreimpl.ui.ChatFragmentBase
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        MviChatModule::class
    ]
)
internal interface MviChatComponent {

    fun inject(fragmentBase: ChatFragmentBase)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance appContext: Context) : MviChatComponent
    }
}