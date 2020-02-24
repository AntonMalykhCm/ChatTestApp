package com.example.chattestapp.di

import com.example.chatimpl.ChatMiddlewareImpl
import com.example.chatimpl.ChatReducerImpl
import com.example.chatimpl.data.ChatState
import com.example.chatimpl.di.ChatUiDependencies
import com.example.chatimpl.middlewares.SocketListenerMiddleware
import com.example.chatimpl.middlewares.UploadNewMessageMiddleware
import com.example.chattestapp.RouterImpl
import com.example.dependencyholder.DependencyHolder
import com.example.mvifeatureapi.api.*
import com.example.mvifeatureapi.di.MviFeatureApi
import com.example.mvifeatureapi.di.MviFeatureDependencies
import com.example.router.Router
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module
class ChatModule {

    @Provides
    @Singleton
    fun provideRouter(chatDependenciesProvider: Provider<ChatUiDependencies>): Router {
        val router = RouterImpl(chatDependenciesProvider)
        DependencyHolder.put(RouterImpl.ROUTER_DEPENDENCY_HOLDER_KEY, router)
        return router
    }

    @Provides
    fun provideChatUiDependencies(mviFeatureApi: MviFeatureApi<ChatState>): ChatUiDependencies {
        return object : ChatUiDependencies {
            override fun getMviFeatureApi(): MviFeatureApi<ChatState> {
                return mviFeatureApi
            }
        }
    }

    @Provides
    fun provideChatMviFeatureApi(
        chatMiddleware: Middleware<ChatState>,
        chatReducer: Reducer<ChatState>
    ): MviFeatureApi<ChatState> {
        return MviFeatureApi.get(
            object : MviFeatureDependencies<ChatState> {
                override fun getMiddleware(): Middleware<ChatState> {
                    return chatMiddleware
                }

                override fun getReducer(): Reducer<ChatState> {
                    return chatReducer
                }
            }
        )
    }

    @Provides
    fun provideChatMiddleware(): Middleware<ChatState> {
        return ChatMiddlewareImpl(
            listOf(
                SocketListenerMiddleware(),
                UploadNewMessageMiddleware()
            )
        )
    }

    @Provides
    fun provideChatReducer(): Reducer<ChatState> {
        return ChatReducerImpl()
    }

}
