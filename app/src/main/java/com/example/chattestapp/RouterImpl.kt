package com.example.chattestapp

import android.content.Context
import com.example.chatimpl.di.ChatUiDependencies
import com.example.chatimpl.ui.ChatActivity
import com.example.router.Router
import javax.inject.Inject
import javax.inject.Provider

class RouterImpl @Inject constructor(
    private val chatUiDependenciesProvider: Provider<ChatUiDependencies>
): Router {

    companion object {
        const val ROUTER_DEPENDENCY_HOLDER_KEY = "ROUTER_DEPENDENCY_HOLDER_KEY"
    }
    override fun toChat(from: Context) {
        ChatActivity.launch(from, chatUiDependenciesProvider.get(), ROUTER_DEPENDENCY_HOLDER_KEY)
    }
}