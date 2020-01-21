package com.example.chattestapp.di

import com.example.chatapi.ChatState
import com.example.chatapi.di.incoming.ChatDependencies
import com.example.chatapi.di.incoming.ChatFragmentProvider
import com.example.chatapi.di.incoming.ChatMiddleware
import com.example.chatapi.di.incoming.ChatReducer
import com.example.chatimpl.ChatFragmentProviderImpl
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
        chatFragmentProvider: ChatFragmentProvider<ChatStateImpl>,
        chatMiddleware: ChatMiddleware<ChatStateImpl>,
        chatReducer: ChatReducer<ChatStateImpl>
    ): ChatDependencies<ChatStateImpl> {
        return object :
            ChatDependencies<ChatStateImpl> {
            override fun getChatFragmentProvider(): ChatFragmentProvider<ChatStateImpl> {
                return chatFragmentProvider
            }

            override fun getChatMiddleware(): ChatMiddleware<ChatStateImpl> {
                return chatMiddleware
            }

            override fun getChatReducer(): ChatReducer<ChatStateImpl> {
                return chatReducer
            }
        }
    }

    @Provides
    fun provideChatFragmentProvider(): ChatFragmentProvider<ChatStateImpl> {
        return ChatFragmentProviderImpl()
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
