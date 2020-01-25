package com.example.chattestapp.di

import com.example.chatapi.di.incoming.ChatDependencies
import com.example.chatapi.di.incoming.ChatMiddleware
import com.example.chatapi.di.incoming.ChatReducer
import com.example.chatimpl.ChatMiddlewareImpl
import com.example.chatimpl.ChatReducerImpl
import com.example.chatimpl.data.ChatStateImpl
import com.example.chattestapp.Router
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module
class ChatModule {

    @Provides
    @Singleton
    fun provideRouter(chatDependenciesProvider: Provider<ChatDependencies<ChatStateImpl>>): Router {
        return Router(chatDependenciesProvider)
    }

    @Provides
    fun provideChatDependencies(
        chatMiddleware: ChatMiddleware<ChatStateImpl>,
        chatReducer: ChatReducer<ChatStateImpl>
    ): ChatDependencies<ChatStateImpl> {
        return object :
            ChatDependencies<ChatStateImpl> {

            override fun getChatMiddleware(): ChatMiddleware<ChatStateImpl> {
                return chatMiddleware
            }

            override fun getChatReducer(): ChatReducer<ChatStateImpl> {
                return chatReducer
            }
        }
    }

    @Provides
    fun provideChatMiddleware(): ChatMiddleware<ChatStateImpl> {
        return ChatMiddlewareImpl()
    }

    @Provides
    fun provideChatReducer(): ChatReducer<ChatStateImpl> {
        return ChatReducerImpl()
    }

}
