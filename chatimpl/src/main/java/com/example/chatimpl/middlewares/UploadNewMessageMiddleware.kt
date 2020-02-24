package com.example.chatimpl.middlewares

import com.example.chatimpl.data.ChatState
import com.example.chatimpl.data.getNextMessageId
import com.example.chatimpl.data.intents.NewMessageIntent
import com.example.chatimpl.data.intents.RenderMessageIntent
import com.example.mvifeatureapi.api.Intent
import com.example.mvifeatureapi.api.IntentDispatcher
import com.example.mvifeatureapi.api.Middleware
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * [Middleware] implementation that imitates pushing new messages to the server.
 */
class UploadNewMessageMiddleware : Middleware<ChatState> {

    private val compositeDisposable = CompositeDisposable()

    override fun apply(state: ChatState?, intent: Intent, dispatcher: IntentDispatcher): Intent {
        if (intent !is NewMessageIntent) {
            return intent
        }
        if (intent == Intent.Stop) {
            compositeDisposable.clear()
            return intent
        }

        val localMessageIntent = RenderMessageIntent(
            message = intent.message,
            isIncoming = false,
            isLocal = true,
            id = getNextMessageId()
        )
        compositeDisposable.add(
            imitateMessageUploading(localMessageIntent)
                .subscribeOn(Schedulers.io())
                .flatMapCompletable { it ->
                    dispatcher.dispatch(it)
                }
                .subscribe()
        )
        return localMessageIntent
    }

    private fun imitateMessageUploading(intent: RenderMessageIntent): Single<RenderMessageIntent> {
        return Observable.timer(3, TimeUnit.SECONDS)
            .flatMap {
                Observable.just(
                    RenderMessageIntent(
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