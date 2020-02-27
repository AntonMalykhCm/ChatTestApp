package com.example.chatmvicoreimpl.feature

import com.badoo.mvicore.element.NewsPublisher
import com.example.chatmvicoreimpl.data.*
import javax.inject.Inject

class NewsPublisherImpl @Inject constructor(): NewsPublisher<Wish, Effect, ChatState, News> {
    private var counter = 0
    override fun invoke(action: Wish, effect: Effect, state: ChatState): News? {
        return CounterNews(++counter)
    }
}