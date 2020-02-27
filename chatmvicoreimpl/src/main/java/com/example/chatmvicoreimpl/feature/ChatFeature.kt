package com.example.chatmvicoreimpl.feature

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.example.chatmvicoreimpl.data.ChatState
import com.example.chatmvicoreimpl.data.Effect
import com.example.chatmvicoreimpl.data.News
import com.example.chatmvicoreimpl.data.Wish

class ChatFeature(
    bootstrapper: Bootstrapper<Wish>,
    actor: Actor<ChatState, Wish, Effect>,
    reducer: Reducer<ChatState, Effect>,
    newsPublisher: NewsPublisher<Wish, Effect, ChatState, News>
) : ActorReducerFeature<Wish, Effect, ChatState, News>(
    ChatState.getDefaultChatState(),
    bootstrapper = bootstrapper,
    actor = actor,
    reducer = reducer,
    newsPublisher = newsPublisher
)