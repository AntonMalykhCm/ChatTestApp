package com.example.chatmvicoreimpl.feature.actors

import com.badoo.mvicore.element.Actor
import com.example.chatmvicoreimpl.data.*
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * [Actor] implementation that imitates pushing new messages to the server.
 */
class UploadNewMessageActor @Inject constructor() : Actor<ChatState, Wish, Effect> {

    override fun invoke(state: ChatState, action: Wish): Observable<out Effect> {

        return when (action) {
            is NewMessageWish -> {
                val localMessage = RenderMessageEffect(
                    message = action.message,
                    isIncoming = false,
                    isLocal = true,
                    id = getNextMessageId()
                )

                Observable.merge(
                    Observable.just(localMessage),
                    imitateMessageUploading(localMessage).toObservable()
                )
            }
            else -> Observable.empty<Effect>()
        }
    }

    private fun imitateMessageUploading(intent: RenderMessageEffect): Single<RenderMessageEffect> {
        return Observable.timer(3, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                Observable.just(
                    RenderMessageEffect(
                        message = intent.message,
                        isIncoming = false,
                        isLocal = false,
                        id = intent.id
                    )
                )
            }
            .firstOrError()
    }
}