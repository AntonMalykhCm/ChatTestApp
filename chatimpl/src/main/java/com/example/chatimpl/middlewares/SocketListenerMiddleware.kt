package com.example.chatimpl.middlewares

import com.example.chatimpl.data.ChatState
import com.example.chatimpl.data.intents.RenderMessageIntent
import com.example.mvifeatureapi.api.Intent
import com.example.mvifeatureapi.api.IntentDispatcher
import com.example.mvifeatureapi.api.Middleware
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * [Middleware] implementation that imitates incoming messages from the external source.
 */
class SocketListenerMiddleware : Middleware<ChatState> {

    private var socketEventsTask: Disposable? = null

    override fun apply(state: ChatState?, intent: Intent, dispatcher: IntentDispatcher): Intent {
        when (intent) {
            Intent.Start -> {
                socketEventsTask = imitateSocketEvents()
                    .subscribeOn(Schedulers.io())
                    .flatMapCompletable {
                        dispatcher.dispatch(it)
                    }
                    .subscribe()

            }
            Intent.Stop -> socketEventsTask?.dispose()
        }
        return intent
    }

    private fun imitateSocketEvents(): Observable<RenderMessageIntent> {
        return Observable.interval(
            5, 10, TimeUnit.SECONDS
        )
            .flatMap {
                Observable.just(
                    RenderMessageIntent(
                        message = "ping: $it",
                        isIncoming = true,
                        isLocal = false
                    )
                )
            }
    }
}