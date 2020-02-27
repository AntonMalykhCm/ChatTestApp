package com.example.chatmvicoreimpl.feature.actors

import com.badoo.mvicore.element.Actor
import com.example.chatmvicoreimpl.data.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * [Actor] implementation that imitates incoming messages from the external source.
 */
class SocketListenerActor @Inject constructor() : Actor<ChatState, Wish, Effect> {

    override fun invoke(state: ChatState, action: Wish): Observable<out Effect> {
        return when (action) {
            is StartFeature -> imitateSocketEvents()
            else -> Observable.empty()
        }
    }

    private fun imitateSocketEvents(): Observable<RenderMessageEffect> {
        return Observable.interval(
            5, 10, TimeUnit.SECONDS
        )
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                Observable.just(
                    RenderMessageEffect(
                        message = "ping: $it",
                        isIncoming = true,
                        isLocal = false
                    )
                )
            }
    }
}