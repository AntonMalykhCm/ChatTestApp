package com.example.chatmvicoreimpl.di

import com.example.chatmvicoreimpl.feature.*
import com.example.chatmvicoreimpl.feature.BinderImpl
import com.example.chatmvicoreimpl.feature.BootstrapperImpl
import com.example.chatmvicoreimpl.feature.actors.ChatActorImpl
import com.example.chatmvicoreimpl.feature.actors.SocketListenerActor
import com.example.chatmvicoreimpl.feature.actors.UploadNewMessageActor
import com.example.chatmvicoreimpl.ui.ChatFragmentBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class MviChatModule {

    @Provides
    @Singleton
    fun provideFeature(
        bootstrapper: BootstrapperImpl,
        actor: ChatActorImpl,
        reducer: ChatReducerImpl,
        news: NewsPublisherImpl
    ) = ChatFeature(bootstrapper, actor, reducer, news)

    @Provides
    fun provideActor(
        socketListenerActor: SocketListenerActor,
        uploadNewMessageActor: UploadNewMessageActor
    ) = ChatActorImpl(
        listOf(
            socketListenerActor,
            uploadNewMessageActor
        )
    )
}
