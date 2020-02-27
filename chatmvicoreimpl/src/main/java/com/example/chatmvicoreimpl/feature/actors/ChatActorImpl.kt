package com.example.chatmvicoreimpl.feature.actors

import com.badoo.mvicore.element.Actor
import com.example.chatmvicoreimpl.data.ChatState
import com.example.chatmvicoreimpl.data.Effect
import com.example.chatmvicoreimpl.data.Wish
import io.reactivex.Observable

internal class ChatActorImpl(
    private val actors: List<Actor<ChatState, Wish, Effect>>
) : Actor<ChatState, Wish, Effect> {

    override fun invoke(state: ChatState, action: Wish): Observable<out Effect> {
        return Observable.merge(
            actors.fold(
                mutableListOf<Observable<out Effect>>(),
                { acc, function ->
                    acc.add(function.invoke(state, action))
                    acc
                }
            )
        )
    }
}