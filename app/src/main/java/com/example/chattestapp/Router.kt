package com.example.chattestapp

import android.content.Context
import com.example.chatapi.di.ChatApi
import com.example.chatapi.di.ChatDependencies
import com.example.chatimpl.data.ChatStateImpl
import com.example.chatimpl.ui.ChatActivityImpl
import com.example.dependencyholder.DependencyHolder
import javax.inject.Inject
import javax.inject.Provider

class Router @Inject constructor(
    private val chatDependenciesProvider: Provider<ChatDependencies<ChatStateImpl>>
) {

    fun openChat(context: Context) {
        val dependencyKey = "chat_dependency_key"
        val chatApi = ChatApi.get(
            chatDependenciesProvider.get()
        )
        val starter = chatApi.getChatStarter()
        DependencyHolder.put(dependencyKey, chatApi)
        starter.startChat(context, ChatActivityImpl::class.java, dependencyKey)
    }
}